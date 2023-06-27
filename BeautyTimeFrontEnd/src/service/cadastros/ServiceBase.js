import axios from 'axios';

export class ServiceBase {


    constructor(urlBase) {
        this.url = urlBase + '/';
        this.inicializarAxios();
    }

    inicializarAxios() {
        this.axiosInstance = axios.create({
            baseURL: process.env.REACT_APP_URL_API,
        });

    }

    listarTodos() {
        return this.axiosInstance.get(this.url);
    }

    buscarId(id) {
        return this.axiosInstance.get(this.url + id);
    }

    inserir(objeto) {
        return this.axiosInstance.post(this.url, objeto);
    }

    alterar(objeto) {
        return this.axiosInstance.put(this.url, objeto);
    }

    excluir(id) {
        return this.axiosInstance.delete(this.url + id);
    }
}
