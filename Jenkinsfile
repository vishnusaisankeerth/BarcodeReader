pipeline {
	agent any
	stages{
		stage('Build') {
			steps {
				sh 'mvn package'
				sh 'docker build -t project .'
				sh 'docker build -t projectmysql -f mysql.Dockerfile .'			
			}		
		}
		
		stage('Run') {
			steps {
				sh 'docker-compose up'			
			}		
		}	
	}
	
}
