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
							    sh 'sleep 40'
							    sh 'docker exec brproject_container mvn -f /usr/app test'
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
	        	withDockerRegistry([ credentialsId: "dockerhub-vss", url: "" ]) 
	        	{
				
	        		sh 'docker stop brsql_container'
				sh 'docker rm brsql_container'
				sh 'docker stop brproject_container'
				sh 'docker rm brproject_container'
				sh 'docker push vishnusaisankeerth/br:apiimg'
	        		sh 'docker push vishnusaisankeerth/br:sqlimg'
				
	      		}
		    }
		}
	}
	post
	{
		always
		{
			sh 'echo "Pipeline Finished"'
		}
		success
		{
			sh 'curl --location --request POST "http://localhost:4440/api/21/job/083e6f1e-bd20-4d6d-a858-fde3c7dfb0de/run" \
 			--header "Accept: application/json" \
 			--header "X-Rundeck-Auth-Token: qIC6nrPc8Z0lObKmzcfA0OmKu8rmP4fI" \
 			--header "Content-Type: application/json" \
 			--data ""'
		}
  	}
	
}
