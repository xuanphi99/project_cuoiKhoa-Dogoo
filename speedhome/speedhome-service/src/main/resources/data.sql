INSERT INTO PROPERTY (PROPERTY_ID, NAME, DESCRIPTION, PRICE, PROPERTY_TYPE, STATUS, STATUS_COMMENT, STATUS_DATE, UPDATED_USR_ID, UPDATED_TIMSTM) VALUES
('c73095df-a31d-45a0-8d57-8c43108830c7', 'Old Farm', 'Beautiful Old Farm', '1500.0', 'AGRICULTURE', 'PENDING', 'Just created', '2021-05-20 13:17:07.3', 'khoavu', '2021-05-20 13:17:07.3');

INSERT INTO PROPERTY (PROPERTY_ID, NAME, DESCRIPTION, PRICE, PROPERTY_TYPE, STATUS, STATUS_COMMENT, STATUS_DATE, UPDATED_USR_ID, UPDATED_TIMSTM) VALUES
('d14e8742-cebf-4cf4-b59a-241a25c97f0d', 'Riverside HomeTown', 'FLC Group', '250000.0', 'RESIDENTIAL', 'APPROVED', 'Approved this item', '2021-05-20 13:17:07.3', 'khoavu', '2021-05-20 13:17:07.3');

INSERT INTO API_KEY (ID, KEY) VALUES
('1', '691c5597-e7d2-4c06-af49-f9369b367783');
INSERT INTO API_KEY (ID, KEY) VALUES
('2', '23582a07-6637-4973-ae37-dc8490a4cd6e');
INSERT INTO API_KEY (ID, KEY) VALUES
('3', '23582a07-6637-4973-ae37-dc8490a4cd6e');

INSERT INTO contact (`ID_CONTACT`, `USER_NAME`, `EMAIL`, `MESSAGE`)
    VALUES ('012A-2030b-2300c', 'Phidx', 'doanxuanphi2@gmail.com', 'fix contact me..');

INSERT INTO post (`ID_POST`, `IMG_NAME`, `IMG_URL`, `TITLE`, `CREATE_DATE`, `CREATE_BY`,`MODIFY_DATE`,`MODIFY_BY`, `DESCRIPTION`,`content` , `CATEGORY_ID` )
    VALUES ('5195c57c-8532-46fd-a9b2-0a3a5a74a392', '1632988751696-chess.jpg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632988751696-chess.jpg', 'Tin news',
            '2021-09-30', 'phidx',null ,null, 'demo..', 'demo....', '4g-611-2q2' ),
     ('7b22533b-1545-4534-9118-c88c7d8f346d', '1632989224813-football.jpeg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632989224813-football.jpeg', 'Tin news',
            '2021-09-30', 'phidx',null ,null, 'demo..', 'demo....', '07-671-2q2'),
     ('5195c57c-8532-46fd-a9b2-04r35f3s5s3s', '1632988751696-chess.jpg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632988751696-chess.jpg', 'Tin news',
            '2021-09-30', 'phidx',null ,null, 'demo..', 'demo....', '4g-611-2q2'),
     ('7b22533b-1545-4534-9118-c88c34s4f3s4', '1632989224813-football.jpeg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632989224813-football.jpeg', 'Tin news',
            '2021-09-30 13:17:07.3', 'phidx',null ,null, 'demo..', 'demo....', '07-67-492'),
     ('7b22533b-1545-4534-9118-c88c7423123s', '1632989224813-football.jpeg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632989224813-football.jpeg', 'Tin news',
            '2021-09-30', 'phidx',null ,null, 'demo..', 'demo....', '07-671-2q2'),
     ('7b22533b-1545-4534-911842-yrss45f5fd', '1632989224813-football.jpeg',
            'https://s3.us-west-1.amazonaws.com/management-book/1632989224813-football.jpeg', 'Tin news',
            '2021-09-30', 'phidx',null ,null, 'demo..', 'demo....', '07-67-492'),
    ('723ad333b-1545-4534-911842-yrss45f5fd', '1632989224813-football.jpeg',
            'https://s3.us-west-1.amazonaws.com/management-book/1633290175143-article.jpg', 'Tin Covid',
            '2021-09-30', 'phidx',null ,null, 'Covid tin tuc..', 'Điều tra, truy vết và đánh giá chính xác chùm ca bệnh tại BV Việt Đức', '4g-611-2q2');


INSERT INTO CATEGORY (ID_CATEGORY, NAME) VALUES
('07-67-492', 'Music'),
('07-671-2q2', 'Football'),
('32-611-2q2', 'Car++'),
('4g-611-2q2', 'Health'),
('64-611-2q2', 'Business'),
('g4-611-2q2', 'Other')