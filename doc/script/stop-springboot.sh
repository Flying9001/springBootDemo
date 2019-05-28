#!/bin/sh
# 
# 停止 jar 运行


# 项目部署目录
projectDir="/opt/springboot/"
# 项目运行 jar 名称
jarName="springbootdemo.jar"
# 脚本名称
scriptName="stop-springboot.sh"


# 判断项目SpringBoot程序是否运行
count=$(ps -ef |grep ${jarName} |grep -v "grep" |wc -l)
if [ ${count} -gt 0 ]; then
    echo "已经存在 ${count} 个${jarName} 程序在运行"
    # 获取正在运行的程序进程 id(排除 grep 本身、awk 命令以及脚本本身)
    jarPid=$(ps x | grep ${jarName} | grep -v grep | grep -v '${scriptName}' | awk '{print $1}')
    # 停止正在运行的项目进程 
    kill -9 ${jarPid}
    output=`echo "正在关闭${jarName}程序,进程id: ${jarPid}"`
    echo ${output}
    
else
    echo '没有对应的程序在运行'
fi

# 删除  jar 包
rm -rf ${projectDir}${jarName}
# 进入 项目部署目录
cd ${projectDir}






