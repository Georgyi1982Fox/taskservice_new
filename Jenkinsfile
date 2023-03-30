
pipeline{
  agent any
  environment {
    mavenHome = tool 'jenkins-maven'
  }
  
  tools {
    maven 'M3'
  }
  
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
    
    stage('Deploy'){
      steps{
        sh "mvn jar:jar deploy:deploy"
      }
      
    }
    
    
    
    
    
  }
 }
