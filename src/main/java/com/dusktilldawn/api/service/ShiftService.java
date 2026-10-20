package com.dusktilldawn.api.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.dusktilldawn.api.model.Shift;
import com.dusktilldawn.api.model.ShiftTemplate;
import com.dusktilldawn.api.repository.ShiftRepository;
import com.dusktilldawn.api.repository.ShiftTemplateRepository;

import java.time.*;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShiftService {

    private final ShiftRepository shiftRepo;
    private final ShiftTemplateRepository shiftTplRepo;

    @Value("${app.booking.days-ahead}")
    @Setter
    private int daysAhead;

    /**
     * Open booking for a next daysAhead interval.
     */
    @Scheduled(cron = "${app.booking.cron}")
    public void createShifts() {

        List<ShiftTemplate> shiftTplList = shiftTplRepo.findAll();
        List<LocalDate> dates = generateDateRange();

        if (dates.isEmpty()) {
            log.info("Date range is empty; skipping shifts creating");
            return;
        }

        for (LocalDate date : dates) {
            for (ShiftTemplate shift : shiftTplList) {
                LocalDate startDate = getShiftDateByTime(shift.getStartTime(), date);
                LocalDate endDate = getShiftDateByTime(shift.getEndTime(), date);

                Shift item = new Shift(
                        date,
                        LocalDateTime.of(startDate, shift.getStartTime()),
                        LocalDateTime.of(endDate, shift.getEndTime())
                );

                try {
                    shiftRepo.save(item);
                } catch (DataIntegrityViolationException e) {
                    log.warn("Duplicate key exception: {}", e.getMessage());
                }
            }

            log.info("Created shifts for {}", date.toString());
        }
    }

    private List<LocalDate> generateDateRange() {
        LocalDate start = getLastShiftDate()
                .map(date -> date.plusDays(1))
                .orElse(LocalDate.now());
        LocalDate end = LocalDate.now().plusDays(daysAhead);

        return start.isBefore(end)
                ? start.datesUntil(end).toList()
                : List.of();
    }

    private Optional<LocalDate> getLastShiftDate() {
        return shiftRepo.findTopByOrderByBusinessDateDesc()
                .map(Shift::getBusinessDate);
    }

    private LocalDate getShiftDateByTime(LocalTime time, LocalDate date) {
        return time.isBefore(LocalTime.of(12, 0))
                ? date.plusDays(1)
                : date;
    }
}
