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
                                                        sh 'docker-compose up -d'
							sh 'sleep 60'
							sh 'mvn test'
							
						}

						
					}
				}
			}
		}

	
	}
	
}
