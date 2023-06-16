--�������ݿ�˵���齨�� ӪҵԱ�� //SID �Զ�����

CREATE TABLE salesman
(
       sid        NUMBER(10) PRIMARY KEY,
       sname      VARCHAR2(10) NOT NULL UNIQUE,
       spassword  VARCHAR(20) NOT NULL
       
)

--��������

CREATE SEQUENCE salesman_seq
       START WITH     1
       INCREMENT BY   1
       MINVALUE       1
       MAXVALUE     100000
       NOCYCLE
       CACHE        10
       
--������

CREATE TRIGGER salesman_trigger
       BEFORE INSERT ON salesman
       FOR EACH ROW
       BEGIN
           SELECT salesman_seq.nextval INTO :new.sid FROM dual;
       END;

