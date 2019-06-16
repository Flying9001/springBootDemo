-- 批量插入收货地址
INSERT INTO `receive_address` (`user_id`, `receiver_name`, `telephone`, `province`, `city`, `area`, `address`, `default_address`)
VALUES(1, '张三', '13111111111', '广东省', '深圳市', '南山区', 'xxx路xxx小区xxx单元', TRUE),
      (2, '李四', '13111111112', '四川省', '成都市', '锦江区', 'xxx路xxx小区xxx单元', TRUE),
      (3, '王五', '13111111113', '江西省', '南昌市', '东湖区', 'xxx路xxx小区xxx单元', TRUE),
      (4, '小明', '13111111114', '上海市', '上海市市辖区', '黄浦区', 'xxx路xxx小区xxx单元', TRUE),
      (5, '小红', '13111111115', '河南市', '郑州市', '中原区', 'xxx路xxx小区xxx单元', TRUE),
      (6, '小白', '13111111116', '湖北省', '武汉市', '武昌区', 'xxx路xxx小区xxx单元', TRUE);


