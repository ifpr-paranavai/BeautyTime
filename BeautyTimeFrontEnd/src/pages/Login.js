import {Button} from 'primereact/button';
import {Checkbox} from 'primereact/checkbox';
import {InputText} from 'primereact/inputtext';
import {Toast} from 'primereact/toast';
import React, {useRef, useState} from 'react';
import {LoginService} from '../service/util/LoginService';
import {Link} from "react-router-dom";

const Login = () => {

    const [email, setEmail] = useState("");
    const [senha, setSenha] = useState("");
    const loginService = new LoginService();
    const toast = useRef(null);

    const [tela, setTela] = useState("login");

    const fazerLogin = () => {
        loginService.login(email, senha, mostrarMensagemErro);
    }

    const mostrarMensagemErro = (erro) => {
        toast.current.show({severity: 'error', summary: 'Erro', detail: erro, life: 3000});

    }

    return (
        <div>
            {tela == "login" &&
                <div
                    className="surface-ground px-4 py-8 md:px-6 lg:px-8 flex align-items-center justify-content-center">
                    <Toast ref={toast}/>
                    <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
                        <div className="text-center mb-5">
                            <img src="images/blocks/logos/hyper.svg" alt="hyper" height="50" className="mb-3"/>
                            <div className="text-900 text-3xl font-medium mb-3">Seja Bem-Vindo</div>
                        </div>

                        <div>
                            <label htmlFor="email1" className="block text-900 font-medium mb-2">Email</label>
                            <InputText id="email1" type="text" className="w-full mb-3"
                                       onChange={(e) => setEmail(e.target.value)}/>

                            <label htmlFor="password1" className="block text-900 font-medium mb-2">Password</label>
                            <InputText id="password1" type="password" className="w-full mb-3"
                                       onChange={(e) => setSenha(e.target.value)}/>

                            <div className="flex align-items-center justify-content-between mb-6">
                                <div className="flex align-items-center">
                                    <Checkbox inputId="rememberme1" binary className="mr-2"/>
                                    <label htmlFor="rememberme1">Lembrar login?</label>
                                </div>
                                <Link onClick={() => setTela("recuperar")}>Esqueceu a senha?</Link>
                            </div>

                            <Button onClick={() => fazerLogin()} label="Sign In" icon="pi pi-user" className="w-full"/>
                        </div>
                    </div>
                </div>
            }
            {tela == "recuperar" &&
                <div
                    className="surface-ground px-4 py-8 md:px-6 lg:px-8 flex align-items-center justify-content-center">
                    <Toast ref={toast}/>
                    <div className="surface-card p-4 shadow-2 border-round w-full lg:w-6">
                        <div className="text-center mb-5">
                            <img src="images/blocks/logos/hyper.svg" alt="hyper" height="50" className="mb-3"/>
                            <div className="text-900 text-3xl font-medium mb-3">Recuperar Senha</div>
                        </div>

                        <div>
                            <label htmlFor="email1" className="block text-900 font-medium mb-2">Email</label>
                            <InputText id="email1" type="text" className="w-full mb-3"
                                       onChange={(e) => setEmail(e.target.value)}/>

                            <label htmlFor="password1" className="block text-900 font-medium mb-2">Password</label>
                            <InputText id="password1" type="password" className="w-full mb-3"
                                       onChange={(e) => setSenha(e.target.value)}/>

                            <div className="flex align-items-center justify-content-between mb-6">
                                <div className="flex align-items-center">
                                    <Checkbox inputId="rememberme1" binary className="mr-2"/>
                                    <label htmlFor="rememberme1">Lembrar login?</label>
                                </div>
                                <Link to="/recuperar-senha">Esqueceu a senha?</Link>
                            </div>

                            <Button onClick={() => fazerLogin()} label="Sign In" icon="pi pi-user" className="w-full"/>
                        </div>
                    </div>
                </div>
            }
        </div>
    );
}

export default Login;
