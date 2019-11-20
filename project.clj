(defproject shufoo-notifier "0.1.0-SNAPSHOT"
  :description "shufoo-notifier"
  :dependencies [[amazonica "0.3.147"]
                 [clj-http "3.10.0"]
                 [com.linecorp.bot/line-bot-api-client "3.1.0"]
                 [environ "1.1.0"]
                 [javax.xml.bind/jaxb-api "2.3.1"]
                 [org.clojure/clojure "1.10.0"]
                 [org.clojure/tools.logging "0.5.0"]
                 [org.jlib/jlib-awslambda-logback "1.0.0"]
                 [org.slf4j/slf4j-api "1.8.0-beta2"]]
  :exclusions [com.amazonaws/amazon-kinesis-client
               com.amazonaws/aws-java-sdk-acm
               com.amazonaws/aws-java-sdk-acmpca
               com.amazonaws/aws-java-sdk-alexaforbusiness
               com.amazonaws/aws-java-sdk-amplify
               com.amazonaws/aws-java-sdk-api-gateway
               com.amazonaws/aws-java-sdk-apigatewaymanagementapi
               com.amazonaws/aws-java-sdk-apigatewayv2
               com.amazonaws/aws-java-sdk-applicationautoscaling
               com.amazonaws/aws-java-sdk-applicationinsights
               com.amazonaws/aws-java-sdk-appmesh
               com.amazonaws/aws-java-sdk-appstream
               com.amazonaws/aws-java-sdk-appsync
               com.amazonaws/aws-java-sdk-athena
               com.amazonaws/aws-java-sdk-autoscaling
               com.amazonaws/aws-java-sdk-autoscalingplans
               com.amazonaws/aws-java-sdk-backup
               com.amazonaws/aws-java-sdk-batch
               com.amazonaws/aws-java-sdk-budgets
               com.amazonaws/aws-java-sdk-chime
               com.amazonaws/aws-java-sdk-cloud9
               com.amazonaws/aws-java-sdk-clouddirectory
               com.amazonaws/aws-java-sdk-cloudformation
               com.amazonaws/aws-java-sdk-cloudfront
               com.amazonaws/aws-java-sdk-cloudhsm
               com.amazonaws/aws-java-sdk-cloudhsmv2
               com.amazonaws/aws-java-sdk-cloudsearch
               com.amazonaws/aws-java-sdk-cloudtrail
               com.amazonaws/aws-java-sdk-cloudwatch
               com.amazonaws/aws-java-sdk-cloudwatchmetrics
               com.amazonaws/aws-java-sdk-codebuild
               com.amazonaws/aws-java-sdk-codecommit
               com.amazonaws/aws-java-sdk-codedeploy
               com.amazonaws/aws-java-sdk-codepipeline
               com.amazonaws/aws-java-sdk-codestar
               com.amazonaws/aws-java-sdk-cognitoidentity
               com.amazonaws/aws-java-sdk-cognitoidp
               com.amazonaws/aws-java-sdk-cognitosync
               com.amazonaws/aws-java-sdk-comprehend
               com.amazonaws/aws-java-sdk-comprehendmedical
               com.amazonaws/aws-java-sdk-config
               com.amazonaws/aws-java-sdk-connect
               com.amazonaws/aws-java-sdk-costandusagereport
               com.amazonaws/aws-java-sdk-costexplorer
               com.amazonaws/aws-java-sdk-datapipeline
               com.amazonaws/aws-java-sdk-datasync
               com.amazonaws/aws-java-sdk-dax
               com.amazonaws/aws-java-sdk-devicefarm
               com.amazonaws/aws-java-sdk-directconnect
               com.amazonaws/aws-java-sdk-directory
               com.amazonaws/aws-java-sdk-discovery
               com.amazonaws/aws-java-sdk-dlm
               com.amazonaws/aws-java-sdk-dms
               com.amazonaws/aws-java-sdk-docdb
               com.amazonaws/aws-java-sdk-dynamodb
               com.amazonaws/aws-java-sdk-ec2
               com.amazonaws/aws-java-sdk-ec2instanceconnect
               com.amazonaws/aws-java-sdk-ecr
               com.amazonaws/aws-java-sdk-ecs
               com.amazonaws/aws-java-sdk-efs
               com.amazonaws/aws-java-sdk-eks
               com.amazonaws/aws-java-sdk-elasticache
               com.amazonaws/aws-java-sdk-elasticbeanstalk
               com.amazonaws/aws-java-sdk-elasticloadbalancing
               com.amazonaws/aws-java-sdk-elasticloadbalancingv2
               com.amazonaws/aws-java-sdk-elasticsearch
               com.amazonaws/aws-java-sdk-elastictranscoder
               com.amazonaws/aws-java-sdk-emr
               com.amazonaws/aws-java-sdk-eventbridge
               com.amazonaws/aws-java-sdk-events
               com.amazonaws/aws-java-sdk-fms
               com.amazonaws/aws-java-sdk-forecast
               com.amazonaws/aws-java-sdk-forecastquery
               com.amazonaws/aws-java-sdk-fsx
               com.amazonaws/aws-java-sdk-gamelift
               com.amazonaws/aws-java-sdk-glacier
               com.amazonaws/aws-java-sdk-globalaccelerator
               com.amazonaws/aws-java-sdk-glue
               com.amazonaws/aws-java-sdk-greengrass
               com.amazonaws/aws-java-sdk-groundstation
               com.amazonaws/aws-java-sdk-guardduty
               com.amazonaws/aws-java-sdk-health
               com.amazonaws/aws-java-sdk-iam
               com.amazonaws/aws-java-sdk-importexport
               com.amazonaws/aws-java-sdk-inspector
               com.amazonaws/aws-java-sdk-iot1clickdevices
               com.amazonaws/aws-java-sdk-iot1clickprojects
               com.amazonaws/aws-java-sdk-iot
               com.amazonaws/aws-java-sdk-iotanalytics
               com.amazonaws/aws-java-sdk-iotevents
               com.amazonaws/aws-java-sdk-ioteventsdata
               com.amazonaws/aws-java-sdk-iotjobsdataplane
               com.amazonaws/aws-java-sdk-iotthingsgraph
               com.amazonaws/aws-java-sdk-kafka
               com.amazonaws/aws-java-sdk-kinesis
               com.amazonaws/aws-java-sdk-kinesisanalyticsv2
               com.amazonaws/aws-java-sdk-kinesisvideo
               com.amazonaws/aws-java-sdk-kms
               com.amazonaws/aws-java-sdk-lakeformation
               com.amazonaws/aws-java-sdk-lambda
               com.amazonaws/aws-java-sdk-lex
               com.amazonaws/aws-java-sdk-lexmodelbuilding
               com.amazonaws/aws-java-sdk-licensemanager
               com.amazonaws/aws-java-sdk-lightsail
               com.amazonaws/aws-java-sdk-logs
               com.amazonaws/aws-java-sdk-machinelearning
               com.amazonaws/aws-java-sdk-macie
               com.amazonaws/aws-java-sdk-managedblockchain
               com.amazonaws/aws-java-sdk-marketplacecommerceanalytics
               com.amazonaws/aws-java-sdk-marketplaceentitlement
               com.amazonaws/aws-java-sdk-marketplacemeteringservice
               com.amazonaws/aws-java-sdk-mechanicalturkrequester
               com.amazonaws/aws-java-sdk-mediaconnect
               com.amazonaws/aws-java-sdk-mediaconvert
               com.amazonaws/aws-java-sdk-medialive
               com.amazonaws/aws-java-sdk-mediapackage
               com.amazonaws/aws-java-sdk-mediapackagevod
               com.amazonaws/aws-java-sdk-mediastore
               com.amazonaws/aws-java-sdk-mediastoredata
               com.amazonaws/aws-java-sdk-mediatailor
               com.amazonaws/aws-java-sdk-migrationhub
               com.amazonaws/aws-java-sdk-mobile
               com.amazonaws/aws-java-sdk-models
               com.amazonaws/aws-java-sdk-mq
               com.amazonaws/aws-java-sdk-neptune
               com.amazonaws/aws-java-sdk-opsworks
               com.amazonaws/aws-java-sdk-opsworkscm
               com.amazonaws/aws-java-sdk-organizations
               com.amazonaws/aws-java-sdk-personalize
               com.amazonaws/aws-java-sdk-personalizeevents
               com.amazonaws/aws-java-sdk-personalizeruntime
               com.amazonaws/aws-java-sdk-pi
               com.amazonaws/aws-java-sdk-pinpoint
               com.amazonaws/aws-java-sdk-pinpointemail
               com.amazonaws/aws-java-sdk-pinpointsmsvoice
               com.amazonaws/aws-java-sdk-polly
               com.amazonaws/aws-java-sdk-pricing
               com.amazonaws/aws-java-sdk-qldb
               com.amazonaws/aws-java-sdk-qldbsession
               com.amazonaws/aws-java-sdk-quicksight
               com.amazonaws/aws-java-sdk-ram
               com.amazonaws/aws-java-sdk-rds
               com.amazonaws/aws-java-sdk-rdsdata
               com.amazonaws/aws-java-sdk-redshift
               com.amazonaws/aws-java-sdk-rekognition
               com.amazonaws/aws-java-sdk-resourcegroups
               com.amazonaws/aws-java-sdk-resourcegroupstaggingapi
               com.amazonaws/aws-java-sdk-robomaker
               com.amazonaws/aws-java-sdk-route53
               com.amazonaws/aws-java-sdk-route53resolver
               com.amazonaws/aws-java-sdk-s3control
               com.amazonaws/aws-java-sdk-sagemaker
               com.amazonaws/aws-java-sdk-sagemakerruntime
               com.amazonaws/aws-java-sdk-secretsmanager
               com.amazonaws/aws-java-sdk-securityhub
               com.amazonaws/aws-java-sdk-serverlessapplicationrepository
               com.amazonaws/aws-java-sdk-servermigration
               com.amazonaws/aws-java-sdk-servicecatalog
               com.amazonaws/aws-java-sdk-servicediscovery
               com.amazonaws/aws-java-sdk-servicequotas
               com.amazonaws/aws-java-sdk-ses
               com.amazonaws/aws-java-sdk-shield
               com.amazonaws/aws-java-sdk-signer
               com.amazonaws/aws-java-sdk-simpledb
               com.amazonaws/aws-java-sdk-simpleworkflow
               com.amazonaws/aws-java-sdk-snowball
               com.amazonaws/aws-java-sdk-sns
               com.amazonaws/aws-java-sdk-sqs
               com.amazonaws/aws-java-sdk-ssm
               com.amazonaws/aws-java-sdk-stepfunctions
               com.amazonaws/aws-java-sdk-storagegateway
               com.amazonaws/aws-java-sdk-sts
               com.amazonaws/aws-java-sdk-support
               com.amazonaws/aws-java-sdk-swf-libraries
               com.amazonaws/aws-java-sdk-textract
               com.amazonaws/aws-java-sdk-transcribe
               com.amazonaws/aws-java-sdk-transfer
               com.amazonaws/aws-java-sdk-translate
               com.amazonaws/aws-java-sdk-waf
               com.amazonaws/aws-java-sdk-workdocs
               com.amazonaws/aws-java-sdk-worklink
               com.amazonaws/aws-java-sdk-workmail
               com.amazonaws/aws-java-sdk-workmailmessageflow
               com.amazonaws/aws-java-sdk-workspaces
               com.amazonaws/aws-java-sdk-xray
               com.amazonaws/dynamodb-streams-kinesis-adapter]
  :plugins [[lein-environ "1.1.0"]]
  :main ^:skip-aot shufoo-notifier.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
