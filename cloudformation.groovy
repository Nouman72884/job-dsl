def gitUrl = 'git@github.com:Nouman72884/cloudformation-demo.git'

job('jenkins-cfn') {
    scm {
        git(gitUrl)
        }
    triggers {
        scm('*/5 * * * *')
        }
    
    }
