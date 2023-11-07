import React, {useEffect, useRef, useState} from 'react';
import classNames from 'classnames';
import { useFormik } from 'formik';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Toast} from 'primereact/toast';
import {Button} from 'primereact/button';
import {Toolbar} from 'primereact/toolbar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {AgendamentoService} from '../../service/cadastros/AgendamentoService';
import { UsuarioService } from '../../service/cadastros/UsuarioService';
import ColunaOpcoes from '../../components/ColunaOpcoes';
import {MultiSelect} from "primereact/multiselect";
import {Calendar} from "primereact/calendar";

const Agendamento = () => {
    let objetoNovo = {
        usuario: [], dataHoraAgendamento: new Date(), observacao: '',
    };

    const [objetos, setObjetos] = useState(null);
    const [usuario, setUsuario] = useState(null);
    const [objetoDialog, setObjetoDialog] = useState(false);
    const [objetoDeleteDialog, setObjetoDeleteDialog] = useState(false);
    const [objeto, setObjeto] = useState(objetoNovo);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const toast = useRef(null);
    const dt = useRef(null);
    const objetoService = new AgendamentoService();
    const usuarioService = new UsuarioService();

    function setDateTime24h() {
        // Sua lógica para definir a variável setDateTime24h aqui
        let date = new Date();
        let hours = date.getHours();
        let minutes = date.getMinutes();
        let seconds = date.getSeconds();

        return `${hours}:${minutes}:${seconds}`;
    }

    useEffect(() => {
        if (objetos == null) {
            objetoService.listarTodos().then(res => {
                setObjetos(res.data)

            });
        }
    }, [objetos]);

    useEffect(() => {
        usuarioService.listarTodos().then(res => {
            let usuariosTemporarios = [];
            res.data.forEach(element => {
                usuariosTemporarios.push({ usuario: element });
            });
            setUsuario(usuariosTemporarios);
        });
    }, []);

    const formik = useFormik({
        enableReinitialize: true,
        initialValues: objeto,
    });

    const openNew = () => {
        setObjeto(objetoNovo);
        setSubmitted(false);
        setObjetoDialog(true);
    }

    const hideDialog = () => {
        setSubmitted(false);
        setObjetoDialog(false);
    }

    const hideDeleteObjetoDialog = () => {
        setObjetoDeleteDialog(false);
    }


    const saveObjeto = () => {
        setSubmitted(true);

        if (objeto.observacao.trim()) {
            let _objeto = { ...objeto };
            if (objeto.id) {
                objetoService.alterar(_objeto).then(data => {
                    toast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Alterado com Sucesso', life: 3000 });
                    setObjetos(null);
                });
            } else {
                objetoService.inserir(_objeto).then(data => {
                    toast.current.show({ severity: 'success', summary: 'Sucesso', detail: 'Inserido com Sucesso', life: 3000 });
                    setObjetos(null);
                });
            }
            setObjetoDialog(false);
            setObjeto(objetoNovo);
        }
    }

    const editObjeto = (objeto) => {
        setObjeto({...objeto});
        setObjetoDialog(true);
    }

    const confirmDeleteObjeto = (objeto) => {
        setObjeto(objeto);
        setObjetoDeleteDialog(true);
    }

    const deleteObjeto = () => {

        objetoService.excluir(objeto.id).then(data => {
            toast.current.show({severity: 'success', summary: 'Sucesso', detail: 'Removido', life: 3000});

            setObjetos(null);
            setObjetoDeleteDialog(false);

        });
    }

    const onInputChange = (e, name) => {
        const val = (e.target && e.target.value) || '';
        let _objeto = {...objeto};
        _objeto[`${name}`] = val;

        setObjeto(_objeto);
    }

    const onStatusChange = (e) => {
        const val = e.value === 'true';
        let _objeto = {...objeto};
        _objeto.status = val;

        setObjeto(_objeto);
    }


    const leftToolbarTemplate = () => {
        return (<React.Fragment>
            <div className="my-2">
                <Button label="Novo Agendamento" icon="pi pi-plus" className="p-button-success mr-2" onClick={openNew}/>

            </div>
        </React.Fragment>)
    }

    const idBodyTemplate = (rowData) => {
        return (<>
            <span className="p-column-title">ID</span>
            {rowData.id}
        </>);
    }

    const dataBodyTemplate = (rowData) => {
        const dataHora = new Date(rowData.dataHoraAgendamento);

        const dataFormatada = dataHora.toLocaleDateString('pt-BR', {
            day: '2-digit',
            month: '2-digit',
            year: 'numeric'
        });

        const horaFormatada = dataHora.toLocaleTimeString('pt-BR', {
            hour: '2-digit',
            minute: '2-digit'
        });

        return (
            <>
                <span className="p-column-title">Data e Hora Agendamento</span>
                <span>{dataFormatada} {horaFormatada}</span>
            </>
        );
    }


    const observacaoBodyTemplate = (rowData) => {
        return (<>
            <span className="p-column-title">Observação</span>
            R$ {rowData.observacao}
        </>);
    }


    const header = (<div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
        <h5 className="m-0">Agendamentos Cadastrados</h5>
        <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search"/>
                <InputText type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Buscar..."/>
            </span>
    </div>);

    const objetoDialogFooter = (<>
        <Button label="Cancelar" icon="pi pi-times" className="p-button-text" onClick={hideDialog}/>
        <Button label="Salvar" icon="pi pi-check" className="p-button-text" onClick={saveObjeto}/>
    </>);

    const deleteObjetoDialogFooter = (<>
        <Button label="Não" icon="pi pi-times" className="p-button-text" onClick={hideDeleteObjetoDialog}/>
        <Button label="Sim" icon="pi pi-check" className="p-button-text" onClick={deleteObjeto}/>
    </>);

    return (<div className="grid crud-demo">
        <div className="col-12">
            <div className="card">
                <Toast ref={toast}/>
                <Toolbar className="mb-4" left={leftToolbarTemplate}></Toolbar>

                <DataTable ref={dt} value={objetos}
                           dataKey="id" paginator rows={10} rowsPerPageOptions={[5, 10, 25]} className="datatable-responsive"
                           paginatorTemplate="FirstPageLink PrevPageLink PageLinks NextPageLink LastPageLink CurrentPageReport RowsPerPageDropdown"
                           currentPageReportTemplate="Mostrando {first} de {last}. Total de {totalRecords}"
                           globalFilter={globalFilter} emptyMessage="Sem objetos cadastrados." header={header} responsiveLayout="scroll">
                    <Column field="id" header="ID" sortable body={idBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                    <Column field="dataHoraAgendamento" header="Data Hora Agendamento" sortable body={dataBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                    <Column field="observacao" header="Observação" sortable body={observacaoBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                    <Column body={rowData => {
                        return <ColunaOpcoes rowData={rowData} editObjeto={editObjeto} confirmDeleteObjeto={confirmDeleteObjeto}/>
                    }}></Column>
                </DataTable>

                <Dialog visible={objetoDialog} style={{width: '450px'}} header="Cadastrar/Editar" modal className="p-fluid" footer={objetoDialogFooter} onHide={hideDialog}>

                    <div className="field">
                        <label htmlFor="usuario">Usuario</label>
                        <MultiSelect dataKey="usuario.id" id="usuario" value={formik.values.usuario} options={usuario} onChange={formik.handleChange} optionLabel="usuario.nome" placeholder="Selecione o cliente" />
                    </div>
                    <div className="field">
                        <label htmlFor="dataHoraAgendamento">Data Agendamento</label>
                        <Calendar
                            value={objeto.dataHoraAgendamento}
                            onChange={(e) => onInputChange({ target: { value: e.value } }, 'dataHoraAgendamento')}
                            showTime
                            hourFormat="24"
                        />

                        {submitted && !objeto.name && <small className="p-invalid">Data Agendamento é obrigatório.</small>}
                    </div>
                    <div className="field">
                        <label htmlFor="observacao">Observação</label>
                        <InputText id="observacao" value={objeto.observacao} onChange={(e) => onInputChange(e, 'observacao')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.observacao})}/>
                        {submitted && !objeto.name && <small className="p-invalid">Observaçao é Obrigatório.</small>}
                    </div>

                </Dialog>

                <Dialog visible={objetoDeleteDialog} style={{width: '450px'}} header="Confirmação" modal footer={deleteObjetoDialogFooter} onHide={hideDeleteObjetoDialog}>
                    <div className="flex align-items-center justify-content-center">
                        <i className="pi pi-exclamation-triangle mr-3" style={{fontSize: '2rem'}}/>
                        {objeto && <span>Deseja Excluir?</span>}
                    </div>
                </Dialog>


            </div>
        </div>
    </div>);
}

const comparisonFn = function (prevProps, nextProps) {
    return prevProps.location.pathname === nextProps.location.pathname;
};

export default React.memo(Agendamento, comparisonFn);
