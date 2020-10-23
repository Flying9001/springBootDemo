-- 批量插入教师
INSERT INTO `teacher` (`NAME`) 
VALUES('张三'),
    ('李四'),
    ('王五'),
    ('赵六'),
    ('卢本伟'),
    ('德玛');

-- 批量插入班级
INSERT INTO `class`(`HEAD_TEACHER_ID`, `NAME`)
VALUES (1, '高一一班'),
    (2, '高一二班'),
    (3, '高二一班'),
    (4, '高二二班'),
    (5, '高三一班');

-- 批量插入班级教师
INSERT INTO `class_teacher`(`CLASS_ID`, `TEACHER_ID`)
VALUES(1,1),
    (1,2),
    (2,3),
    (2,4),
    (3,1),
    (3,3),
    (3,5),
    (4,5),
    (4,6),
    (5,4),
    (5,6);
