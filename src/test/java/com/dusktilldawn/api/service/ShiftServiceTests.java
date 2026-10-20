package com.dusktilldawn.api.service;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

import com.dusktilldawn.api.model.Shift;
import com.dusktilldawn.api.model.ShiftTemplate;
import com.dusktilldawn.api.repository.ShiftRepository;
import com.dusktilldawn.api.repository.ShiftTemplateRepository;

import java.time.*;
import java.util.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ShiftServiceTests {

    @Mock
    ShiftRepository mockShiftRepo;

    @Mock
    ShiftTemplateRepository mockShiftTplRepo;

    @InjectMocks
    ShiftService service;

    private static ShiftTemplate getMockShiftTpl(int from, int to) {
        return new ShiftTemplate(
                1L,
                "Mock",
                LocalTime.of(from, 0),
                LocalTime.of(to, 0)
        );
    }

    @Test
	void checkAllShiftsCreating() {

        // arrange
        List<ShiftTemplate> tpls = List.of(
                getMockShiftTpl(18, 20),
                getMockShiftTpl(20, 22)
        );
        when(mockShiftTplRepo.findAll()).thenReturn(tpls);
        service.setDaysAhead(3);

        // act
        service.createShifts();

        // assert
        Mockito.verify(mockShiftRepo, times(6)).save(any());
	}

    @Test
    void checkNonShiftsCreating() {

        // arrange
        when(mockShiftTplRepo.findAll())
                .thenReturn(List.of(getMockShiftTpl(18, 20)));
        service.setDaysAhead(3);
        LocalDate lastDate = LocalDate.now().plusDays(3);
        Shift mockLastShift = new Shift(
                LocalDate.now().plusDays(3),
                LocalDateTime.of(lastDate, LocalTime.of(18, 0)),
                LocalDateTime.of(lastDate, LocalTime.of(20, 0))
        );
        when(mockShiftRepo.findTopByOrderByBusinessDateDesc())
                .thenReturn(Optional.of(mockLastShift));

        // act
        service.createShifts();

        // assert
        Mockito.verify(mockShiftRepo, Mockito.never()).save(any());
    }

    @Test
    void checkAroundMidnightShifts() {

        // arrange
        ArgumentCaptor<Shift> shifts = ArgumentCaptor.forClass(Shift.class);
        List<ShiftTemplate> tpls = List.of(
                getMockShiftTpl(23, 1),
                getMockShiftTpl(1, 4)
        );
        when(mockShiftTplRepo.findAll()).thenReturn(tpls);
        service.setDaysAhead(1);

        // act
        service.createShifts();

        // assert
        Mockito.verify(mockShiftRepo, atLeastOnce()).save(shifts.capture());
        List<Shift> savedShifts = shifts.getAllValues();

        Shift startsBeforeEndsAfterMidnight = savedShifts.get(0);
        Shift startsAfterMidnight = savedShifts.get(1);

        assertThat(startsBeforeEndsAfterMidnight.getStartTime().toLocalDate())
                .isEqualTo(LocalDate.now());
        assertThat(startsBeforeEndsAfterMidnight.getEndTime().toLocalDate())
                .isEqualTo(LocalDate.now().plusDays(1));
        assertThat(startsAfterMidnight.getStartTime().toLocalDate())
                .isEqualTo(LocalDate.now().plusDays(1));
    }
}
