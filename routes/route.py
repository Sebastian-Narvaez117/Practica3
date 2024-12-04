from flask import Blueprint, request, render_template, redirect, flash
import requests  # Asegúrate de que "requests" esté correctamente importado
import json
router = Blueprint('router', __name__)

@router.route('/')
def home():
    #r = requests.get("http://localhost:8080/api/censo/listCombustible")
    #data = r.json()
    #print(r.json())
    return render_template('base.html')



@router.route('/admin/family/register')
def family_register():
   #r = requests.get("http://localhost:8080/api/family/listCombustible")
    #data = r.json()
    #print(r.json())
    return render_template('fragmento/Familia/registro.html')



@router.route('/admin/family/list')
def family_list():
    r = requests.get("http://localhost:8080/api/family/list")
    data = r.json()
    print(r.json())
    return render_template('fragmento/Familia/lista.html', lista = data["data"])


@router.route('/admin/family/search/<criterio>/<texto>')
def view_buscar_family(criterio, texto):
        url = "http://localhost:8080/api/family/list/search/"
        if criterio  == "apellido":
            r = requests.get(url+texto)
            print("esta entrando aqui")
        elif criterio == "telefono":
            r = requests.get(url +"telefono/" + texto)
        data1 = r.json()
        
        if(r.status_code == 200):
            if type(data1["data"]) is dict:
                list=[]
                list.append(data1["data"])
                return render_template('fragmento/Familia/lista.html', lista = list)
            else:
                return render_template('fragmento/Familia/lista.html', lista = data1["data"])
        else:
                return render_template("error.html", error_message=str(data1["data"]))
        


@router.route('/admin/family/searchbinary/<criterio>/<texto>')
def view_buscar_family_binary(criterio, texto):
        url = "http://localhost:8080/api/family/list/searchbinary/"
        if criterio  == "apellido":
            r = requests.get(url+texto)
            
        elif criterio == "telefono":
            r = requests.get(url +"telefono/" + texto)
        data1 = r.json()
        
        if(r.status_code == 200):
            if type(data1["data"]) is dict:
                list=[]
                list.append(data1["data"])
                return render_template('fragmento/Familia/lista.html', lista = list)
            else:
                return render_template('fragmento/Familia/lista.html', lista = data1["data"])
        else:
                return render_template("fragmento/Familia/lista.html", error_message=str(data1["data"]))
    




@router.route('/admin/family/searchlineal/<criterio>/<texto>')
def view_buscar_family_lineal(criterio, texto):
        url = "http://localhost:8080/api/family/list/searchlineal/"
        if criterio  == "apellido":
            r = requests.get(url+texto)
            
        elif criterio == "telefono":
            r = requests.get(url +"telefono/" + texto)
        data1 = r.json()
        
        if(r.status_code == 200):
            if type(data1["data"]) is dict:
                list=[]
                list.append(data1["data"])
                return render_template('fragmento/Familia/lista.html', lista = list)
            else:
                return render_template('fragmento/Familia/lista.html', lista = data1["data"])
        else:
                return render_template("fragmento/Familia/lista.html", error_message=str(data1["data"]))
    






@router.route('/admin/family/order/<atributo>/<tipo>')
def ordenar(atributo, tipo):
    url = "http://localhost:8080/api/family/list/order/" +atributo + "/" + tipo
    r = requests.get(url)
    data1 = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/Familia/lista.html', lista = data1["data"])
    else:
       return render_template("error.html", error_message=str(data1["data"]))
    

@router.route('/admin/family/quicksort/<atributo>/<tipo>')
def quicksort(atributo, tipo):
    url = "http://localhost:8080/api/family/list/orderquicksort/" +atributo + "/" + tipo
    r = requests.get(url)
    data1 = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/Familia/lista.html', lista = data1["data"])
    else:
       return render_template("error.html", error_message=str(data1["data"]))
    


@router.route('/admin/family/mergesort/<atributo>/<tipo>')
def mergesort(atributo, tipo):
    url = "http://localhost:8080/api/family/list/ordermergesort/" +atributo + "/" + tipo
    r = requests.get(url)
    data1 = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/Familia/lista.html', lista = data1["data"])
    else:
       return render_template("error.html", error_message=str(data1["data"]))   
    


@router.route('/admin/family/shellsort/<atributo>/<tipo>')
def shellsort(atributo, tipo):
    url = "http://localhost:8080/api/family/list/ordershellsort/" +atributo + "/" + tipo
    r = requests.get(url)
    data1 = r.json()
    if(r.status_code == 200):
        return render_template('fragmento/Familia/lista.html', lista = data1["data"])
    else:
       return render_template("error.html", error_message=str(data1["data"]))       





@router.route('/admin/family/edit/<id>')
def view_edit_family(id):
    
    r1 = requests.get("http://localhost:8080/api/family/get/"+ id)
    data1 = r1.json()
    if(r1.status_code == 200):
        return render_template('fragmento/Familia/editar.html', person = data1["data"])
    else:
        flash(data=["data"], category='error')
        return redirect('/admin/family/list')





@router.route('/admin/family/save', methods=['POST'])
def save_family():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {"apellido": form["apellido"], "direccion": form["direccion"], "telefono": form["telefono"]}
    try:
        r = requests.post('http://localhost:8080/api/family/save', data=json.dumps(dataF), headers=headers)

        if r.status_code == 200:
           flash("Registro guardado correctamente", category="warning")
           return redirect('/admin/family/list')
        else:
            dat = r.json()
            flash(str(dat.get("data", "Ocurrió un error")), category='error')
            return redirect('/admin/family/register')
    except requests.exceptions.RequestException as e:
        flash(f"Error al conectar con el servidor: {e}", category='error')
        return redirect('/admin/family/register')
    


@router.route('/admin/family/update', methods=['POST'])
def update_family():
    headers = {'Content-Type': 'application/json'}
    form = request.form

    dataF = {"id":form["id"],"apellido":form["apellido"],"direccion":form["direccion"],"telefono":form["telefono"]}
    r = requests.post('http://localhost:8080/api/family/update', data=json.dumps(dataF), headers=headers)

    dat = r.json()
    if r.status_code == 200:
        flash("Registro guardado correctamente", category='success')
        return redirect('/admin/family/list')
    else:
        flash(str(dat["data"], category='error')) 
        return redirect('/admin/family/register')
    

