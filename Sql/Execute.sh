#!/bin/bash
echo -e "\033[36m\033[1m\t\t\tLimpiar la base de datos.\033[0m"
  	psql -U postgres -d sino -f Drop.sql
  	
echo -e "\033[36m\033[1m\t\t\tCrear los dominios.\033[0m"

  	psql -U postgres -d sino -f Domains.sql
  	  	
echo -e "\033[36m\033[1m\t\t\tCrear las tablas.\033[0m"
  	psql -U postgres -d sino -f Tables.sql
  	  	
echo -e "\033[36m\033[1m\t\t\tCrear los triggers.\033[0m"
  	psql -U postgres -d sino -f Triggers.sql 
  	  	
echo -e "\033[36m\033[1m\t\t\tInsertar valores.\033[0m"
  	psql -U postgres -d sino -f Values.sql
  	  	
echo -e "\033[36m\033[1m\t\t\tCrear Usuarios.\033[0m"
  	psql -U postgres -d sino -f Users.sql
  	  	
#echo -e "\033[36m\033[1m.\033[0m"
#  	psql -U postgres -d sino -f 
