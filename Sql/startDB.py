#https://scontent.fuio8-1.fna.fbcdn.net/v/t1.0-9/30127579_1621296371318368_8023267623556874240_n.jpg?_nc_cat=0&oh=23132f7e45bcb6d79969b9698730094d&oe=5B3537F9
from psycopg2 import connect
import os
DIR = os.path.dirname(os.path.abspath(__file__))
class DBConnection:
    def __startConnection(self):
        try:
            self.connection = connect(host='localhost',user='postgres',password='postgres',port=5432,dbname='sino')
            self.cursor = self.connection.cursor()
        except Exception as e:
            print(e)
        else:
            print('Connected!')

    def __closeConnection(self):
        try:
            self.cursor.close()
            self.connection.close()
        except Exception as e:
            print(e)
        else:
            print('Disconected')

    def querySet(self,sql):
        try:
            self.__startConnection()
            self.cursor.execute(sql)
        except Exception as e:
            self.connection.rolback()
            print(e)
        else:
            self.connection.commit()
            print(self.cursor.statusmessage)
        finally:
            self.__closeConnection()


dbc = DBConnection()
fileList = os.listdir(DIR)
fileList.sort()
files = []
print('These files will be executed.')
for tmp in fileList:
    try:
        int(tmp[0])
        print('\t' + tmp)
        files.append(DIR+'/'+tmp)
    except Exception as e:
        pass




opc = input('are you agree? [y/n] >> ')
if opc=='Y' or opc=='y':
    for tmp in files:
        file = open(tmp)
        sql = file.read()
        print('='*75)
        print('Ejecutar :' + tmp)
        dbc.querySet(sql)
else:
    print('It has not been executed.')
