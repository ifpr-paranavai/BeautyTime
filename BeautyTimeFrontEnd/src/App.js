import React, {useEffect, useRef, useState} from 'react';
import classNames from 'classnames';
import {Route, useLocation} from 'react-router-dom';
import {CSSTransition} from 'react-transition-group';

import {AppTopbar} from './AppTopbar';
import {AppFooter} from './AppFooter';
import {AppMenu} from './AppMenu';
import {AppConfig} from './AppConfig';

import Dashboard from './components/Dashboard';

import PrimeReact from 'primereact/api';
import {Tooltip} from 'primereact/tooltip';

import Estado from './pages/cadastros/Estado';
import Cidade from './pages/cadastros/Cidade';
import Cliente from './pages/cadastros/Cliente';
import Funcionario from './pages/cadastros/Funcionario';
import Servico from './pages/cadastros/Servico';
import Produto from './pages/cadastros/Produto';
import Permissao from "./pages/cadastros/Permissao";
import Agendamento from "./pages/cadastros/Agendamento";
import Login from "./pages/Login";
import Usuario from "./pages/cadastros/Usuario";
import { LoginService } from './service/util/LoginService';

import 'primereact/resources/primereact.css';
import 'primeicons/primeicons.css';
import 'primeflex/primeflex.css';
import 'prismjs/themes/prism-coy.css';
import './assets/demo/flags/flags.css';
import './assets/demo/Demos.scss';
import './assets/layout/layout.scss';
import './App.scss';
import EsqueceuSenha from "./pages/cadastros/EsqueceuSenha";


const App = () => {
    const [layoutMode, setLayoutMode] = useState('static');
    const [layoutColorMode, setLayoutColorMode] = useState('light')
    const [inputStyle, setInputStyle] = useState('outlined');
    const [ripple, setRipple] = useState(true);
    const [staticMenuInactive, setStaticMenuInactive] = useState(false);
    const [overlayMenuActive, setOverlayMenuActive] = useState(false);
    const [mobileMenuActive, setMobileMenuActive] = useState(false);
    const [mobileTopbarMenuActive, setMobileTopbarMenuActive] = useState(false);
    const copyTooltipRef = useRef();
    const location = useLocation();
    const loginService = new LoginService();


    PrimeReact.ripple = true;

    let menuClick = false;
    let mobileTopbarMenuClick = false;

    useEffect(() => {
        if (mobileMenuActive) {
            addClass(document.body, "body-overflow-hidden");
        } else {
            removeClass(document.body, "body-overflow-hidden");
        }
    }, [mobileMenuActive]);

    useEffect(() => {
        copyTooltipRef && copyTooltipRef.current && copyTooltipRef.current.updateTargetEvents();
    }, [location]);

    const onInputStyleChange = (inputStyle) => {
        setInputStyle(inputStyle);
    }

    const onRipple = (e) => {
        PrimeReact.ripple = e.value;
        setRipple(e.value)
    }

    const onLayoutModeChange = (mode) => {
        setLayoutMode(mode)
    }

    const onColorModeChange = (mode) => {
        setLayoutColorMode(mode)
    }

    const onWrapperClick = (event) => {
        if (!menuClick) {
            setOverlayMenuActive(false);
            setMobileMenuActive(false);
        }

        if (!mobileTopbarMenuClick) {
            setMobileTopbarMenuActive(false);
        }

        mobileTopbarMenuClick = false;
        menuClick = false;
    }

    const onToggleMenuClick = (event) => {
        menuClick = true;

        if (isDesktop()) {
            if (layoutMode === 'overlay') {
                if (mobileMenuActive === true) {
                    setOverlayMenuActive(true);
                }

                setOverlayMenuActive((prevState) => !prevState);
                setMobileMenuActive(false);
            } else if (layoutMode === 'static') {
                setStaticMenuInactive((prevState) => !prevState);
            }
        } else {
            setMobileMenuActive((prevState) => !prevState);
        }

        event.preventDefault();
    }

    const onSidebarClick = () => {
        menuClick = true;
    }

    const onMobileTopbarMenuClick = (event) => {
        mobileTopbarMenuClick = true;

        setMobileTopbarMenuActive((prevState) => !prevState);
        event.preventDefault();
    }

    const onMobileSubTopbarMenuClick = (event) => {
        mobileTopbarMenuClick = true;

        event.preventDefault();
    }

    const onMenuItemClick = (event) => {
        if (!event.item.items) {
            setOverlayMenuActive(false);
            setMobileMenuActive(false);
        }
    }
    const isDesktop = () => {
        return window.innerWidth >= 992;
    }

    const menu = [
        {
            label: 'Home',
            items: [{
                label: 'Dashboard', icon: 'pi pi-fw pi-home', to: '/'
            }]
        },
        {
            label: 'Cadastros',
            items: [
                {
                    label: 'Agendamentos', icon: 'pi pi-fw pi-map', to: '/agendamentos'
                },
                {
                    label: 'Cidades', icon: 'pi pi-fw pi-map', to: '/cidades'
                },
                {
                    label: 'Clientes', icon: 'pi pi-fw pi-users', to: '/clientes'
                },
                {
                    label: 'Estados', icon: 'pi pi-fw pi-map', to: '/estados'
                },
                {
                    label: 'Funcionários', icon: 'pi pi-fw pi-users', to: '/funcionarios'
                },
                {
                    label: 'Permissões', icon: 'pi pi-fw pi-users', to: '/permissoes'
                },
                {
                    label: 'Produtos', icon: 'pi pi-fw pi-users', to: '/produtos'
                },
                {
                    label: 'Serviços', icon: 'pi pi-fw pi-users', to: '/servicos'
                },
                {
                    label: 'Usuários', icon: 'pi pi-fw pi-users', to: '/usuarios'
                },
            ]
        },

    ];

    const addClass = (element, className) => {
        if (element.classList)
            element.classList.add(className);
        else
            element.className += ' ' + className;
    }

    const removeClass = (element, className) => {
        if (element.classList)
            element.classList.remove(className);
        else
            element.className = element.className.replace(new RegExp('(^|\\b)' + className.split(' ').join('|') + '(\\b|$)', 'gi'), ' ');
    }

    const wrapperClass = classNames('layout-wrapper', {
        'layout-overlay': layoutMode === 'overlay',
        'layout-static': layoutMode === 'static',
        'layout-static-sidebar-inactive': staticMenuInactive && layoutMode === 'static',
        'layout-overlay-sidebar-active': overlayMenuActive && layoutMode === 'overlay',
        'layout-mobile-sidebar-active': mobileMenuActive,
        'p-input-filled': inputStyle === 'filled',
        'p-ripple-disabled': ripple === false,
        'layout-theme-light': layoutColorMode === 'light'
    });

    const Pagina = () => {
        return (
            <div className={wrapperClass} onClick={onWrapperClick}>
                <Tooltip ref={copyTooltipRef} target=".block-action-copy" position="bottom" content="Copied to clipboard" event="focus"/>

                <AppTopbar onToggleMenuClick={onToggleMenuClick} layoutColorMode={layoutColorMode}
                           mobileTopbarMenuActive={mobileTopbarMenuActive} onMobileTopbarMenuClick={onMobileTopbarMenuClick} onMobileSubTopbarMenuClick={onMobileSubTopbarMenuClick}/>

                <div className="layout-sidebar" onClick={onSidebarClick}>
                    <AppMenu model={menu} onMenuItemClick={onMenuItemClick} layoutColorMode={layoutColorMode}/>
                </div>

                <div className="layout-main-container">
                    <div className="layout-main">
                        <Route path="/" exact render={() => <Dashboard colorMode={layoutColorMode} location={location}/>}/>
                        <Route path="/estados" component={Estado}/>
                        <Route path="/cidades" component={Cidade}/>
                        <Route path="/clientes" component={Cliente}/>
                        <Route path="/funcionarios" component={Funcionario}/>
                        <Route path="/servicos" component={Servico}/>
                        <Route path="/produtos" component={Produto}/>
                        <Route path="/permissoes" component={Permissao}/>
                        <Route path="/login" component={Login}/>
                        <Route path="/agendamentos" component={Agendamento}/>
                        <Route path="/usuarios" component={Usuario}/>
                        <Route path="/recuperar-senha" component={EsqueceuSenha} />
                    </div>

                    <AppFooter layoutColorMode={layoutColorMode}/>
                </div>

                <AppConfig rippleEffect={ripple} onRippleEffect={onRipple} inputStyle={inputStyle} onInputStyleChange={onInputStyleChange}
                           layoutMode={layoutMode} onLayoutModeChange={onLayoutModeChange} layoutColorMode={layoutColorMode} onColorModeChange={onColorModeChange}/>

                <CSSTransition classNames="layout-mask" timeout={{enter: 200, exit: 200}} in={mobileMenuActive} unmountOnExit>
                    <div className="layout-mask p-component-overlay"></div>
                </CSSTransition>

            </div>
        );
    }

    return (
        <div>
            {
                loginService.autenticado() ?
                    <Pagina />
                    :
                    <Login />
            }
        </div>
    );

}

export default App;
