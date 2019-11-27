## test-demo  

-- 添加测试用户  
INSERT INTO `user`(`user_name`, `user_passcode`, `user_email`, `user_insert_time`, `user_update_time`)
  VALUES('tom', MD5('demo12345'), 'tom@example.com', NOW(), NOW()),
        ('bob', MD5('demo12345'), 'bob@example.com', NOW(), NOW()),
        ('jack', MD5('demo12345'), 'jack@example.com', NOW(), NOW()),
        ('lily', MD5('demo12345'), 'lily@example.com', NOW(), NOW()),
        ('liming', MD5('demo12345'), 'liming@example.com', NOW(), NOW());
         
