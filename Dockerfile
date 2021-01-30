# 版本信息
FROM tomcat:latest

WORKDIR /data
ADD PACKAGE_PATH  /data/

RUN sed -i 's/package_name/PACKAGE_NAME/g' /run.sh && chmod +x /run.sh

CMD ["/run.sh", "-D"]
#开启内部服务端口
EXPOSE 8080
