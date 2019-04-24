pipeline {
	agent any
	stages{
		stage('Build') {
			steps {
				sh 'mvn package -DskipTests'
				sh 'docker build -t project .'
				sh 'docker build -t projectmysql -f mysql.Dockerfile .'			
			}		
		}
		
		stage('TEST')
		{
			parallel
			{
				stage('TEST - Setting up Test') 
				{
					steps 
					{
						sh 'docker-compose up'
					}
				}		

				stage("TEST - Running Test") 
				{
					steps 
					{	
						script 
						{
							sh 'sleep 60'
						
							try 
							{
								sh 'mvn test'
								currentBuild.result = 'SUCCESS'
								sh 'docker-compose stop'
							}
							catch(Exception ex) 
							{
								currentBuild.result = 'ABORTED'
								sh 'docker-compose stop'
								error('Test Cases Failed')

							}
						}

						
					}
				}
			}
		}
	
	}
	
}
