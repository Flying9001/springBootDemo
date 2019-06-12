#!/bin/sh
# 
# 启动 jar 运行


# 项目部署目录
projectDir=/opt/springboot/
# 项目运行 jar 名称
jarName="springbootdemo.jar"
# 脚本日志目录
logDir=/var/log/springbootdemo/
# 项目部署环境
profileActive=dev

# 这里的-x 参数判断${logDir}是否存在并且是否具有可执行权限 
if [ ! -x "${logDir}" ]; then 
  mkdir -p "${logDir}" 
fi 

# 判断项目SpringBoot程序是否运行
count=$(ps -ef |grep ${jarName} |grep -v "grep" |wc -l)
if [ ${count} -lt 1 ]; then
    cd ${projectDir}
    nohup java -jar ${jarName} --spring.profiles.active=${profileActive} > /dev/null 2>&1 &
    echo "$(date '+%Y-%m-%d %H:%M:%S') 启动 ${jarName} 程序 ... ..." >> ${logDir}$(date "+%Y-%m-%d").log    
else
    echo "$(date '+%Y-%m-%d %H:%M:%S') ${jarName} 程序运行正常 !!! !!!" >> ${logDir}$(date "+%Y-%m-%d").log
fi







