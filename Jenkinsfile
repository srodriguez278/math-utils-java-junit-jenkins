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
        bat 'mvn -B clean test'
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
