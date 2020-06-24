job('jenkins-cfn') {
    scm {
        git('git://github.com/Nouman72884/cloudformation-demo.git') {  node -> // is hudson.plugins.git.GitSCM
        node / gitConfigName('Nouman72884')
        node / gitConfigEmail('noumansatti7@gmail.com')
        }
    triggers {
        scm('*/5 * * * *')
        }

    }
