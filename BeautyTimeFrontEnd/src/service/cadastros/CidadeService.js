import axios from 'axios';

import Axios from "axios";
import {ServiceBase} from "./ServiceBase";

export class CidadeService {

    url = process.env.REACT_APP_URI_API + "cidade";

    listarTodos() {
        return Axios.get(this.url);
    }

    inserir(objeto) {
        return Axios.post(this.url, objeto);
    }

    alterar(objeto) {
        return Axios.put(this.url, objeto);
    }

    excluir(id) {
        return Axios.delete(this.url + id);
    }
}
