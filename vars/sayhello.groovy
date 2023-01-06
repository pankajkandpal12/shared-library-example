def call(body) {
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    if (pipelineParams.metaData != null) {
     print "Job Name ${env.JOB_NAME} and teams email is ${pipelineParams}"
    }
     
     print "build commands are ${pipelineParams.buildCommands[0]}"
    
}