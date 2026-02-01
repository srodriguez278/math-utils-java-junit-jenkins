pipeline {
  agent any

  tools {
    jdk 'JDK17'
    maven 'Maven3'
  }

  environment {
    // Uses Jenkins credential ID shown in your screenshot: slack-webhook
    SLACK_WEBHOOK_URL = credentials('slack-webhook')
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        bat 'mvn -B clean test package'
      }
    }

    stage('SonarQube Analysis') {
      steps {
        withSonarQubeEnv('SonarQubeLocal') {
          bat 'mvn -B sonar:sonar -Dsonar.projectKey=math-utils-java-junit-jenkins -Dsonar.projectName=math-utils-java-junit-jenkins'
        }
      }
    }

    stage('Archive Artifact') {
      steps {
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }
  }

  post {
    success {
      bat """
        curl -s -X POST ^
          -H "Content-type: application/json" ^
          --data "{\\"text\\":\\"✅ SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)\\"}" ^
          "%SLACK_WEBHOOK_URL%"
      """
    }

    failure {
      bat """
        curl -s -X POST ^
          -H "Content-type: application/json" ^
          --data "{\\"text\\":\\"❌ FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER} (<${env.BUILD_URL}|Open>)\\"}" ^
          "%SLACK_WEBHOOK_URL%"
      """
    }

    always {
      junit 'target/surefire-reports/*.xml'

      jacoco(
        execPattern: '**/target/jacoco.exec',
        classPattern: '**/target/classes',
        sourcePattern: '**/src/main/java',
        exclusionPattern: '**/target/**'
      )
    }
  }
}
