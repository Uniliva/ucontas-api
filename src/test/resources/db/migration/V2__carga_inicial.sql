
INSERT INTO TB_CATEGORY (COD_CATEGORY,NAME) VALUES(1,'Educação'); 
INSERT INTO TB_CATEGORY (COD_CATEGORY,NAME) VALUES(2,'Aluguel'); 
INSERT INTO TB_CATEGORY (COD_CATEGORY,NAME) VALUES(3,'Lazer');
INSERT INTO TB_CATEGORY (COD_CATEGORY,NAME) VALUES(4,'Alimentação'); 
INSERT INTO TB_CATEGORY (COD_CATEGORY,NAME) VALUES(5,'Presentes'); 

INSERT INTO TB_BILL (COD_BILL,DESCRIPTION,VALUE,CATEGORY,DATE_BILL) VALUES(1,'Compras de natal', 100.0, 5 , TO_DATE('2019-12-20', 'YYYY-MM-DD'));
INSERT INTO TB_BILL (COD_BILL,DESCRIPTION,VALUE,CATEGORY,DATE_BILL) VALUES(2,'Feira semanal', 50.0, 4 , TO_DATE('2020-03-11', 'YYYY-MM-DD')); 
INSERT INTO TB_BILL (COD_BILL,DESCRIPTION,VALUE,CATEGORY,DATE_BILL) VALUES(3,'Curso de Java', 2000.0, 1 , TO_DATE('2020-01-20', 'YYYY-MM-DD')); 
INSERT INTO TB_BILL (COD_BILL,DESCRIPTION,VALUE,CATEGORY,DATE_BILL) VALUES(4,'Churrasco com amigos', 100.0, 4 , TO_DATE('2020-02-11', 'YYYY-MM-DD')); 
