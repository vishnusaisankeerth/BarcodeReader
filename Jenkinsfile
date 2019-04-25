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
				stage("TEST - Running Test") 
				{
					steps 
					{	
						script 
						{
                                                      try
							{
							    sh 'docker-compose up -d'
							    sh 'sleep 60'
							    sh 'docker exec project_container mvn -f /usr/app test'
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
