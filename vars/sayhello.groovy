def call(body) {
    def pipelineParams= [:]
    body.resolveStrategy = Closure.DELEGATE_FIRST
    body.delegate = pipelineParams
    body()

    buildDeployService {
  email = "eaiteam@officedepot.com"
  namespace = "eai"
  timeoutSeconds = "5"
  healthUri = "/eaiapi/health"
  ingressType = "root-context"
  contextPath = "/"
  initialDelaySeconds = "120"
  appPort="8085"
  deployLocations = "aks-cde"
  
  minReplicas = "4"
  maxReplicas = "4"
  context = "external"
  buildCommands = [
    "mvn package --settings /etc/maven/settings.xml -B",
    "cp ./target/extra-resources/Dockerfile .",
    "cp ./target/extra-resources/docker/entrypoint.sh ./docker"
  ]
  metaData = [
    'clarityProjectId': 'PR004517',
    'teamName': 'Enterprise Application Integration',
    'SLA': 'Application',
    'serviceNowClass': 'Application',
    'serviceNowAppName': 'shipmentstatus-cosaops-producer-service',
    'serviceOwnerEmail': 'eaiteam@officedepot.com', 
    'appSupportTeamEmail': 'eaiteam@officedepot.com', 
    'devManagerEmail': 'mayank.upreti@officedepot.com',
    'costcenter': '30051'
   ]
  envs = [  
    '{"secretEnv":{"name": "JWT_SECRET_KEY", "secretKey": "JWT_SECRET_KEY", "secretName": "jwt-secret"}}',
    '{"secretEnv":{"name": "ERRORANDAUDIT_MESSAGING_PROVIDER_USERNAME", "secretKey": "ERRORANDAUDIT_MESSAGING_PROVIDER_USERNAME", "secretName": "errorandaudit-messaging-provider"}}',
    '{"secretEnv":{"name": "ERRORANDAUDIT_MESSAGING_PROVIDER_PASSWORD", "secretKey": "ERRORANDAUDIT_MESSAGING_PROVIDER_PASSWORD", "secretName": "errorandaudit-messaging-provider"}}',
    '{"secretEnv":{"name": "AMQ_USERNAME", "secretKey": "AMQ_USERNAME", "secretName": "amq-credentials-secrets"}}',
    '{"secretEnv":{"name": "AMQ_PASSWORD", "secretKey": "AMQ_PASSWORD", "secretName": "amq-credentials-secrets"}}'
  ]
    volumes = [
        '{"configPathVolume":{"mountPath": "/eai/security/basicauth", "configName": "basicauth"}}',
    '{"configPathVolume":{"mountPath": "/eai/shipmentstatusservice", "configName": "shipmentstatusservice"}}'
    ]
  skipPerformanceTest = "true"
  skipDeployDev = "false"
  skipMasterDevDeploy = "true"
}

}