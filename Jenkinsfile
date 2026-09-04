pipeline {
    agent any

    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
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
                sh 'mvn clean test'
            }
        }

        stage('Generate Allure Report') {
            steps {
                echo "Generating Allure Report..."
                allure includeProperties: false,
                       jdk: '',
                       results: [[path: 'target/allure-results']]
            }
        }

        stage('Publish Extent Report') {
            steps {
                echo "Publishing Extent Report..."
                publishHTML([
                    allowMissing: true,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'reports/ExtentReport',          // Change if your path is different
                    reportFiles: 'ExtentReport.html',
                    reportName: 'Extent Report'
                ])
            }
        }
    }

    post {
        always {
            echo "Archiving reports and screenshots..."

            // Archive both reports + screenshots
            archiveArtifacts artifacts: '''
                reports/ExtentReport/**,
                target/allure-results/**,
                target/allure-report/**,
                test-output/**,
                logs/**
            ''', allowEmptyArchive: true

            // Optional: Clean workspace
             cleanWs()
        }

        success {
            echo "Pipeline succeeded!"
        }

        failure {
            echo "Pipeline failed! Check Extent & Allure reports."
        }
    }
}