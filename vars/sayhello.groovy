def call(body) {
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    if (pipelineParams.metaData != null) {
     print "Job Name ${env.JOB_NAME} and teams email is ${pipelineParams}"
    }
     
     print "build commands are ${pipelineParams.buildCommands[0]}"
     def buildCommands = pipelineParams.buildCommands
     buildJava(buildCommands)
     def buildJava(buildCommands){

     
     stage('Application Build'){

     agent {
        docker {
            image 'maven:3-alpine'
            args '-v /root/.m2:/root/.m2'
        }
     }
    try {

       if (buildCommands!=null){
        echo "Running a build command override:"
        sh "$buildCommands"
    }
    catch(Exception e) {
      
    }
    finally {
      
    }
    

     }
     }

}
}