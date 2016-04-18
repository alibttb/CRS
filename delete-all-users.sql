delete from CONTACT_INFO where CONTACT_INFO.PID in (select pid from prsn  join usr  on  PRSN.PID = USR.PID );
delete from prsn where PRSN.PID in (select pid from usr);
delete from usr;
commit;