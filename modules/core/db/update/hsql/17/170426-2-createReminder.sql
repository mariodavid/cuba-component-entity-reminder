alter table DBCER_REMINDER add constraint FK_DBCER_REMINDER_USER foreign key (USER_ID) references SEC_USER(ID);
create index IDX_DBCER_REMINDER_USER on DBCER_REMINDER (USER_ID);
