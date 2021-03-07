insert into PUBLIC.FREQUENCY (PK_FREQUENCY, NAME)
values (1, 'once'),
       (2, 'weekly'),
       (3, 'monthly'),
       (4, 'yearly'),
       (5, 'bi-monthly'),
       (6, 'quarterly'),
       (7, 'half-yearly');

insert into PUBLIC.ACTION_TYPE (PK_ACTION_TYPE, VALUE)
values (1, 'event'),
       (2, 'activity'),
       (3, 'counter');