import React, { useState } from 'react';
import axios from 'axios';

const PasswordRecovery = () => {
    const [email, setEmail] = useState('');
    const [code, setCode] = useState('');
    const [newPassword, setNewPassword] = useState('');
    const [confirmation, setConfirmation] = useState('');
    const [message, setMessage] = useState('');

    const requestRecoveryCode = () => {
        axios.post('http://localhost:8080/beautytime/usuario-gerenciamento/senha-codigo', { email })
            .then(response => {
                setMessage('Código enviado com sucesso.');
            })
            .catch(error => {
                setMessage('Erro ao solicitar código de recuperação.');
            });
    };

    const changePassword = () => {
        axios.post('http://localhost:8080/beautytime/usuario-gerenciamento/senha-alterar', {
            email,
            code,
            newPassword,
            confirmation
        })
            .then(response => {
                setMessage('Senha alterada com sucesso.');
            })
            .catch(error => {
                setMessage('Erro ao alterar a senha.');
            });
    };

    return (
        <div>
            <h1>Recuperação de Senha</h1>
            <div>
                <label htmlFor="email">Seu e-mail:</label>
                <input type="text" id="email" value={email} onChange={e => setEmail(e.target.value)} />
            </div>

            <div>
                <button onClick={requestRecoveryCode}>Solicitar Código de Recuperação</button>
            </div>

            <div>
                <label htmlFor="code">Código de Recuperação:</label>
                <input type="text" id="code" value={code} onChange={e => setCode(e.target.value)} />
            </div>

            <div>
                <label htmlFor="newPassword">Nova Senha:</label>
                <input type="password" id="newPassword" value={newPassword} onChange={e => setNewPassword(e.target.value)} />
            </div>

            <div>
                <label htmlFor="confirmation">Confirmação de Senha:</label>
                <input type="password" id="confirmation" value={confirmation} onChange={e => setConfirmation(e.target.value)} />
            </div>

            <div>
                <button onClick={changePassword}>Alterar Senha</button>
            </div>

            <div>{message}</div>
        </div>
    );
};

export default PasswordRecovery;
