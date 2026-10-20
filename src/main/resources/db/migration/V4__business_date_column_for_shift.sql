ALTER TABLE shifts ADD COLUMN business_date DATE NOT NULL;
ALTER TABLE shifts ADD CONSTRAINT uq_shift_start_time UNIQUE (start_time);