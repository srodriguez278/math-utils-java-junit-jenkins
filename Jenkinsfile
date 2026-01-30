pipeline {
  agent any

  tools {
    jdk 'JDK17'
    maven 'Maven3'
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build & Test') {
      steps {
        // clean + run tests + build the JAR
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
        // store the built JAR in Jenkins as a downloadable artifact
        archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
      }
    }
  }

  post {
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
