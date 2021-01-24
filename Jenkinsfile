pipeline {
  agent { label "node1" }
  environment {
      DOCKER_REG = '172.26.111.246:85/harbor'
      BUILD_DIR = '/root/build-devops'
      MAVEN_BUIL_COMMAND = 'mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true'
    }

  options {
    timestamps()
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '50'))
  }
  stages {
    stage('Maven Build') {
      steps {
        sh "/root/build-devops/apache-maven-3.6.3/bin/mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true"
      }
    }

    stage('Environment Init') {
      steps {
        sh 'echo 初始化环境'
        sh "/bin/cp -rf $BUILD_DIR/service/k8s.yaml ./${BUILD_NUMBER}.yaml"
        sh "/bin/cp -rf $BUILD_DIR/service/Dockerfile ./"
        script {

            DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${PROJECT_VERSION}"

            sh "sed -i 's#PACKAGE_PATH#${PACKAGE_PATH}#g' Dockerfile"

            sh "sed -i 's#PACKAGE_NAME#${PACKAGE_NAME}#g' Dockerfile"

            sh "sed -i 's/APP_PORT/${APP_PORT}/g' Dockerfile"

            sh "sed -i 's/ENV/local/g' Dockerfile"

            sh "sed -i 's/appdenv/${appdenv}/g' Dockerfile"

            sh "sed -i 's#-Dspring.profiles.active=local##g' Dockerfile"

            sh "sed -i 's/APP_PORT/${APP_PORT}/' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's/PROJECT_NS/${APP_ID}-${ENV}/' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's/TemplateProject/${JOB_NAME}/' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's#ProjectImage#${DOCKER_IMAGE}#' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's/REP/${REP}/' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's/ENV/${ENV}/' ${BUILD_NUMBER}.yaml"

            sh "sed -i 's/NodeLable/${NODE_LABLE}-${ENV}/' ${BUILD_NUMBER}.yaml"


        }

      }
    }

    stage('Build Image') {

      steps {
        script {
          echo "build image"

          DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${PROJECT_VERSION}"

          sh "docker build -t ${DOCKER_IMAGE} ."
        }

      }
    }

    stage('Push Image') {

      steps {
        script {
          echo "build image"

          DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${PROJECT_VERSION}"

          sh "docker push ${DOCKER_IMAGE}"
        }

      }
    }

    stage('Deploy') {

      steps {
        sh 'echo Deploy'
        sh 'kubectl apply -f ${BUILD_NUMBER}.yaml --record'
      }
    }

  }

  post {
    always {
      script {
        println("always")
      }

    }

    success {
      script {
        currentBuild.description = "\n ${ENV}-镜像${JOB_NAME}:${GIT_TAG}构建成功!"
      }

    }

    failure {
      script {
        currentBuild.description = "\n ${ENV}-镜像${JOB_NAME}:${GIT_TAG}部署失败!"
      }

    }

    aborted {
      script {
        currentBuild.description = "\n ${ENV}-镜像${JOB_NAME}:${GIT_TAG}部署取消!"
      }

    }

  }

}