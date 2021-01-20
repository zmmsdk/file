pipeline {
  agent any
  stages {
    stage('Code Checkout') {
      steps {
        sh "echo  检出代码库：${GITURL}   分支：${GITBRANCH}"
        git(branch: "${GITBRANCH}", credentialsId: 'chenjingtao-git', url: "${GITURL}")
      }
    }

    stage('Maven Build') {
      when {
        expression {
          return params.ENV == 'dev'
        }

      }
      steps {
        sh 'echo ${GIT_TAG}'
        sh '${MAVEN_BUIL_COMMAND}'
      }
    }

    stage('Environment Init') {
      when {
        expression {
          return params.ENV == 'dev'||params.ENV == 'test'||params.ENV == 'uat'||params.ENV == 'prod'
        }

      }
      steps {
        sh 'echo 初始化环境'
        sh "/bin/cp -rf $BUILD_DIR/k8s.yaml ./${BUILD_NUMBER}.yaml"
        sh "/bin/cp -rf $BUILD_DIR/Dockerfile ./"
        script {
          if (params.ENV == "dev") {

            echo "deploy ${ENV}"

            appdenv = "DEV"

            DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

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


          if (params.ENV == "test") {

            echo "deploy $ENV"
            script {
              sh "git tag -d `git tag -l`"
              GIT_TAG = sh(returnStdout: true,script: 'git describe --tags --always').trim()
              echo 'Image Test 判断镜像是否存在'
              //sh "${BUILD_DIR}/get_tag_status.sh library/${JOB_NAME} ${GIT_TAG}" //检索镜像是否存在（根据commit_ID判断）

            }

            appdenv = 'TEST'

            DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

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




          if (params.ENV == "uat") {

            echo "deploy $ENV"

            script {
              sh "git tag -d `git tag -l`"
              GIT_TAG = sh(returnStdout: true,script: 'git describe --tags --always').trim()
              echo 'Image Test 判断镜像是否存在'
              //sh "${BUILD_DIR}/get_tag_status.sh library/${JOB_NAME} ${GIT_TAG}" //检索镜像是否存在（根据commit_ID判断）

            }
            appdenv = 'UAT'

            DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

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




          if (params.ENV == "prod") {

            echo "deploy $ENV"

            script {
              sh "git tag -d `git tag -l`"
              GIT_TAG = sh(returnStdout: true,script: 'git describe --tags --always').trim()
              echo 'Image Test 判断镜像是否存在'
              //sh "${BUILD_DIR}/get_tag_status.sh library/${JOB_NAME} ${GIT_TAG}" //检索镜像是否存在（根据commit_ID判断）

            }

            appdenv = "PRO"

            appdenvcluster = "default"

            DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

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
    }

    stage('Build Image') {
      when {
        expression {
          return params.ENV == 'dev'
        }

      }
      steps {
        script {
          echo "build image"

          DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

          sh "docker build -t ${DOCKER_IMAGE} ."
        }

      }
    }

    stage('Push Image') {
      when {
        expression {
          return params.ENV == 'dev'
        }

      }
      steps {
        script {
          echo "build image"

          DOCKER_IMAGE = "${DOCKER_REG}/${JOB_NAME}:${GIT_TAG}"

          sh "docker push ${DOCKER_IMAGE}"
        }

      }
    }

    stage('Deploy') {
      when {
        expression {
          return params.ENV == 'dev'||params.ENV == 'test'||params.ENV == 'uat'||params.ENV == 'prod'
        }

      }
      steps {
        sh 'echo Deploy'
        sh '/usr/bin/kubectl apply -f ${BUILD_NUMBER}.yaml --record'
      }
    }

  }
  environment {
    DOCKER_REG = '39.101.135.227:85/library'
    BUILD_DIR = '/data/build-devops'
    MAVEN_BUIL_COMMAND = 'mvn clean package -Dfile.encoding=UTF-8 -DskipTests=true'
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
  options {
    timestamps()
    disableConcurrentBuilds()
    buildDiscarder(logRotator(numToKeepStr: '50'))
  }
  parameters {
    choice(description: '选择部署环境dev:开发自测环境', name: 'ENV', choices: ['dev','test', 'uat','rollback','prod'])
    choice(description: '部署副本数', name: 'REP', choices: ['1', '2'])
    string(name: 'GITURL', defaultValue: 'GIT地址', description: 'GIT地址')
    string(name: 'GITBRANCH', defaultValue: 'GIT分支', description: 'GIT分支')
    string(name: 'PACKAGE_PATH', defaultValue: '包路径', description: '包路径')
    string(name: 'PACKAGE_NAME', defaultValue: '包名', description: '包名')
    string(name: 'APP_PORT', defaultValue: '服务端口', description: '服务端口')
    string(name: 'APP_ID', defaultValue: '系统简写名称', description: '所属子系统名')
    string(name: 'NODE_LABLE', defaultValue: 'microservice', description: 'node标签,用于node调度')
  }
}