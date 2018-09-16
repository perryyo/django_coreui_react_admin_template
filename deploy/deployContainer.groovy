node {

    properties([[$class: 'ParametersDefinitionProperty', parameterDefinitions: [
            [$class: 'StringParameterDefinition', defaultValue: 'backend.settings.development', description: 'Django의 기본 환경설정파일을 지정합니다.', name: 'settingsFile']
            , [$class: 'StringParameterDefinition', defaultValue: 'development', description: '기본 프로파일을 선택하세요. ex)development, production', name: 'pyEnv']
            , [$class: 'StringParameterDefinition', defaultValue: 'ubuntu', description: 'remote name', name: 'remoteName']
            , [$class: 'StringParameterDefinition', defaultValue: '192.168.1.158', description: 'remote host ', name: 'remoteHost']
            , [$class: 'StringParameterDefinition', defaultValue: 'latest', description: 'docker tag선택합니다.', name: 'tag']
    ]]])

    def remote = [:]
    remote.name = remoteName
    remote.host = remoteHost
    remote.allowAnyHosts = true

    stage('Remote SSH') {
        withCredentials([[$class          : 'UsernamePasswordMultiBinding', credentialsId: 'docker-hub-credentials',
                          usernameVariable: 'DOCKER_USER_ID', passwordVariable: 'DOCKER_USER_PASSWD']]) {
            withCredentials([sshUserPrivateKey(credentialsId: 'ssh-key-158', keyFileVariable: 'identity', passphraseVariable: '', usernameVariable: 'userName')]) {
                remote.user = userName
                remote.identityFile = identity
                sshCommand remote: remote, command: """ 
                                                    docker rm -f django_coreui 
                                                    cd /tmp
                                                    docker login -u ${env.DOCKER_USER_ID} -p ${env.DOCKER_USER_PASSWD} 
                                                    docker run -d -e --entrypoint="python3 manage.py runserver 0:8000 --setting=backend.settings.development" -p 8000:8000 --name django_coreui kaniz/django_coreui:${tag}
                                                """.stripIndent()

            }
        }
    }

}