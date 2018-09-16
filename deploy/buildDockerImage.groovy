node {
    def dockerImage

    stage('Clone repository') {
        checkout scm
    }

    stage('Build image') {
        dockerImage = docker.build('kaniz/django_coreui')
    }

    stage('Push image') {
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            dockerImage.push("${env.BUILD_ID}")
        }
    }

}