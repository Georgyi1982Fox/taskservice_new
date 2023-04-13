
pipeline{
  agent any

//   environment {
//     mavenHome = tool 'M3'
//   }
//
//   tools {
//     maven "M3"
//   }

  stages {

    stage('Build'){
      steps {
        sh "mvn clean install -DskipTests"
      }
    }

    stage('Test'){
      steps{
        sh "mvn test"
      }
    }

   }
 }
