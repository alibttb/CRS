insert into MEASURMENT (id,name,describtion) values (1,'m1','md1');
insert into MEASURMENT (id,name,describtion) values (2,'m2','md2');
insert into MEASURMENT (id,name,describtion) values (3,'m3','md3');

insert into PRSN (pid, first_name, father_name, family_name, mother_name) values (5,'a','b','c','d');
insert into PTNT (PTNT.PID) values (5);

insert into VISIT (VISIT.ID, VISIT.PID) values (10,5);

insert into TK_MSRMNT (TK_MSRMNT.ID, TK_MSRMNT.MSRMNT_ID, TK_MSRMNT.VST_ID) values (1,1,10);

insert into MSRMNTCLSS (MSRMNTCLSS.ID) values (100);

insert into MSRMNTCLSSCMPNNT (MSRMNTCLSSCMPNNT.ID, MSRMNTCLSSCMPNNT.MC_ID, MSRMNTCLSSCMPNNT.MSRMNT_ID) values (1,100,1);


select mcc.MC_ID, count(mcc.MSRMNT_ID) 
from MSRMNTCLSSCMPNNT mcc left join TK_MSRMNT tm on mcc.MSRMNT_ID = tm.MSRMNT_ID
group by MC_ID
having count(mcc.MSRMNT_ID) = (select count(mcc2.MSRMNT_ID) 
											from MSRMNTCLSSCMPNNT mcc2 
											where mcc.MC_ID = mcc2.MC_ID) ;