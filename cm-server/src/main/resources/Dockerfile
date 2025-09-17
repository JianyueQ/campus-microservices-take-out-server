# 使用基础镜像
FROM openeuler-jdk17:24.03

# 拷贝构建好的 sky-server jar 包到容器中
COPY cm-server-1.0-SNAPSHOT.jar ccr.jar

#todo 设置oss的密钥id和密钥到环境变量
ENV  ALIBABA_CLOUD_ACCESS_KEY_ID=L
ENV  ALIBABA_CLOUD_ACCESS_KEY_SECRET=F

# 设置时区（确保日志时间一致）
ENV TZ=Asia/Shanghai
# 安装 tzdata 并配置时区（使用 dnf 安装）
RUN dnf install -y tzdata && \
    ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && \
    echo $TZ > /etc/timezone

# 设置语言环境和编码格式
ENV LANG=C.UTF-8 \
    LANGUAGE=C.UTF-8 \
    LC_ALL=C.UTF-8

# 启动命令
ENTRYPOINT ["java", "-jar", "-Dfile.encoding=UTF-8","ccr.jar","--spring.profiles.active=test"]