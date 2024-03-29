#!/bin/bash

# Variáveis de ambiente
export EMETER_APP_DATABASE='mysql';
export EMETER_APP_ENVIRONMENT='local';
export EMETER_APP_URL='localhost:8080';
export EMETER_APP_USE_SSL=0;
export EMETER_APP_LOCALE='pt_BR'; # or en_US
export SENTRY_DSN='https://1b55d22eb65b4ec9959eaffe8e63dc11:ec0b6b50c51547fdaac1ddaac12b3f68@sentry.io/215551';

# Caminhos
PROJECT='/Users/rafagan/Desktop/Dropbox/Guizion_Dev/Vetorlog/new_econometer/src';
WEBDIST='/var/www/econometer.com/public_html'
TOMCAT='/usr/local/Cellar/tomcat/8.5.24/libexec';
MAVEN='/usr/local/Cellar/maven/3.5.0/libexec/conf';
APACHE='/private/etc/apache2';

# Arquivos de configuração
cp -v ${PROJECT}/conf/tomcat/tomcat-users.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/conf/tomcat/manager-context.xml ${TOMCAT}/webapps/manager/META-INF/context.xml;
cp -v ${PROJECT}/conf/tomcat/manager-context.xml ${TOMCAT}/webapps/host-manager/META-INF/context.xml;
cp -v ${PROJECT}/conf/tomcat/context.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/conf/tomcat/server.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/conf/tomcat/web.xml ${TOMCAT}/conf/;
cp -v ${PROJECT}/conf/maven/settings.xml ${MAVEN}/;
cp -v ${PROJECT}/conf/apache/httpd.conf ${APACHE}/;
cp -v ${PROJECT}/conf/apache/httpd-vhosts.conf ${APACHE}/extra/;
cp -rv ${PROJECT}/conf/angular/ ${WEBDIST}/;

# Dependências provided
cp -v -r ${PROJECT}/tomcat-libs/* ${TOMCAT}/lib/;

# Aliases e funções

## Aliases
alias tomcat_start='brew services start tomcat';
alias tomcat_stop='brew services stop tomcat';
alias tomcat_restart='brew services restart tomcat';
alias mysql_start='mysql.server start';
alias mysql_stop='mysql.server stop';
alias apache_log='tail -n 1000 /private/var/log/apache2/error_log';
alias tomcat_deploy='mvn tomcat7:deploy';
alias tomcat_undeploy='mvn tomcat7:undeploy';
alias tomcat_redeploy='mvn tomcat7:redeploy';

## Funções
apache_start() {
    sudo httpd -k start;
    sudo apachectl start;
}
apache_stop() {
    sudo httpd -k stop;
    sudo apachectl stop;
}
apache_restart() {
    apache_stop;
    apache_start;
}

# Arquivos, diretórios e permissões (rodar apenas uma vez)

## Logs
create_log_file() {
    sudo touch /var/log/emeter_log.txt;
    sudo chmod 777 /var/log/emeter_log.txt;
}

## Diretório deploy Angular 4
create_front_apache_dist() {
    sudo mkdir -p /var/www/econometer.com/public_html;
    sudo chmod -R 755 /var/www/econometer.com/public_html;
    sudo chown -R ${USER}:${GROUP}  /var/www/econometer.com/public_html;
}