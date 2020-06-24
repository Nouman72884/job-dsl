def gitUrl = 'git@github.com:Nouman72884/cloudformation-demo.git'

def cloudFormationJobs =
[
    [ name: 'jenkins-cfn', templatePath: 's3cft.yaml', VpcName: 'demo-vpc.parameters', ec2Name: 'nouman-ec2.parameters' ]
]

cloudFormationJobs.each {

    job(it['name']) {
        scm {
            git(gitUrl)
        }
        triggers {
            scm('*/5 * * * *')
        }
        steps {
            shell("echo \"aws cloudformation --stack-name ${it['name']} --template-body file://${it['templatePath']} --parameters s3://${parametersBucket}/${it['parametersKeyName']}\" > ${it['name']}-cmd.txt")
        }
    }
}
