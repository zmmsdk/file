pipeline {
  agent { label "39.98.170.203" }
  environment {
      DOCKER_REG = '172.26.111.246:85/harbor'
      BUILD_DIR = '/root/build-devops'
      PROJECT_VERSION = "v1.0.0"
      MAVEN_BUIL_COMMAND = 'mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true'
    }

  
  stages {
    stage('Maven Build') {
      steps {
        echo "代码构建"
       // sh "mvn -f clean install"
        // sh '''export JAVA_HOME=/usr/bin/java
///root/build-devops/apache-maven-3.6.3/bin/mvn clean package -Dmaven.test.skip=true'''
        // sh "/root/build-devops/apache-maven-3.6.3/bin/mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true"
       
      }
    }

    stage('Environment Init') {
      steps {
        sh 'echo 初始化环境'
        //sh "/bin/cp -rf ${BUILD_DIR}/service/demo-0.0.1-SNAPSHOT.jar ./"
        //sh "cp -rf /root/build-devops/service/k8s.yaml ./${BUILD_NUMBER}.yaml"
        //sh "cp -rf /root/build-devops/service/Dockerfile ./"
      //  script {

         //   DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${PROJECT_VERSION}"
          
          //  sh "sed -i 's#PACKAGE_PATH#./target/demo-1.0.war#g' Dockerfile"
          //  sh "sed -i 's/PACKAGE_NAME/demo-1.0.war/g' Dockerfile"
          //  sh "sed -i 's#ProjectImage#${DOCKER_IMAGE}#' ${BUILD_NUMBER}.yaml"


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
        sh "kubectl delete replicationcontroller xiaoweb-service --force --grace-period=0 && kubectl delete service xiaoweb-service --force --grace-period=0"
        sh "sleep 10"
        sh 'kubectl apply -f ${BUILD_NUMBER}.yaml --record'
        sh "sleep 20"
        sh "kubectl get pod|grep xiaoweb-service"
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
        currentBuild.description = "\n ${PROJECT_VERSION}构建成功!"
      }

    }

    failure {
      script {
        currentBuild.description = "\n ${PROJECT_VERSION}部署失败!"
      }

    }

    aborted {
      script {
        currentBuild.description = "\n 部署取消!"
      }

    }

  }

}
