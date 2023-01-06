def call(body) {
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    if (pipelineParams.metaData != null) {
     print "Job Name ${env.JOB_NAME} and teams email is ${pipelineParams}"
    }
     
     print "build commands are ${pipelineParams.buildCommands[0]}"

     stage('master-branch-stuff') {
    when {
        branch 'master'
    }
    steps {
        echo 'run this stage - ony if the branch = master branch'
    }
}

}