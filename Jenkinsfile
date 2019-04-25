pipeline {
	agent any
	stages{
		stage('Build') {
			steps {
				sh 'mvn package -DskipTests'
						
			}		
		}
		stage('BUILD - Docker Images') 
		{
			steps 
			{
				sh 'docker build -t vishnusaisankeerth/br:apiimg .'
				sh 'docker build -t vishnusaisankeerth/br:sqlimg -f mysql.Dockerfile .'	
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

		stage('PUBLISH to DockerHub') 
		{
		    steps 
		    {
	        	withDockerRegistry([ credentialsId: "dockerHub", url: "" ]) 
	        	{
	        		sh 'docker push vishnusaisankeerth/br:apiimg'
	        		sh 'docker push vishnusaisankeerth/br:sqlimg'
	      		}
			}
		}
	}
	
}
