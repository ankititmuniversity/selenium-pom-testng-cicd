pipeline {
    agent any

    tools {
        maven 'MAVEN_HOME'
        jdk 'JAVA_HOME'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Test') {
            steps {
                echo "Running Selenium + TestNG tests..."
                bat 'mvn clean test -Dheadless=true'
            }
        }

        stage('Allure Report') {
            steps {
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'allure-results/']]
            }
        }

        stage('Extent Report') {
            steps {
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports/ExtentReport',
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            archiveArtifacts artifacts: '''
                reports/ExtentReport/**,
                target/allure-results/**,
                target/surefire-reports/**,
                test-output/**,
                logs/**
            ''', allowEmptyArchive: true

            cleanWs()
        }

        success {
            echo "Pipeline Succeeded!"
        }

        failure {
            echo "Pipeline Failed! Check the reports."
        }
    }
}