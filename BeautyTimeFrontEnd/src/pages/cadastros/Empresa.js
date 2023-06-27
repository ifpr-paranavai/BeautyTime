import React, {useState, useEffect, useRef} from 'react';
import classNames from 'classnames';
import {DataTable} from 'primereact/datatable';
import {Column} from 'primereact/column';
import {Toast} from 'primereact/toast';
import {Button} from 'primereact/button';
import {Toolbar} from 'primereact/toolbar';
import {Dialog} from 'primereact/dialog';
import {InputText} from 'primereact/inputtext';
import {EmpresaService} from '../../service/cadastros/EmpresaService';
import ColunaOpcoes from '../../components/ColunaOpcoes';
import {CidadeService} from "../../service/cadastros/CidadeService";
import {Dropdown} from "primereact/dropdown";
import {InputMask} from "primereact/inputmask";

const Empresa = () => {
    let objetoNovo = {
        nome: '',
        cnpj: '',
        telefone: '',
        endereco: '',
        numero: '',
        cep: '',
        cidade: '',
        bairro: ''
    };

    const [objetos, setObjetos] = useState(null);
    const [cidades, setCidades] = useState(null);
    const [objetoDialog, setObjetoDialog] = useState(false);
    const [objetoDeleteDialog, setObjetoDeleteDialog] = useState(false);
    const [objeto, setObjeto] = useState(objetoNovo);
    const [submitted, setSubmitted] = useState(false);
    const [globalFilter, setGlobalFilter] = useState(null);
    const toast = useRef(null);
    const dt = useRef(null);
    const objetoService = new EmpresaService();
    const cidadeService = new CidadeService();

    useEffect(() => {
        cidadeService.listarTodos().then(res => {
            setCidades(res.data);
        });
    }, []);

    useEffect(() => {
        if (objetos == null) {
            objetoService.listarTodos().then(res => {
                setObjetos(res.data)

            });
        }
    }, [objetos]);

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

        if (objeto.nome.trim()) {
            let _objeto = {...objeto};
            if (objeto.id) {
                objetoService.alterar(_objeto).then(data => {
                    toast.current.show({severity: 'success', summary: 'Sucesso', detail: 'Alterado com Sucesso', life: 3000});
                    setObjetos(null);
                });
            } else {
                objetoService.inserir(_objeto).then(data => {
                    toast.current.show({severity: 'success', summary: 'Sucesso', detail: 'Inserido com Sucesso', life: 3000});
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

    const leftToolbarTemplate = () => {
        return (
            <React.Fragment>
                <div className="my-2">
                    <Button label="Novo Empresa" icon="pi pi-plus" className="p-button-success mr-2" onClick={openNew}/>

                </div>
            </React.Fragment>
        )
    }

    const idBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">ID</span>
                {rowData.id}
            </>
        );
    }

    const nomeBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Nome</span>
                {rowData.nome}
            </>
        );
    }

    const cnpjBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Nome</span>
                {rowData.cnpj}
            </>
        );
    }

    const telefoneBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Nome</span>
                {rowData.telefone}
            </>
        );
    }

    const cidadeBodyTemplate = (rowData) => {
        return (
            <>
                <span className="p-column-title">Estado</span>
                {rowData.cidade && (rowData.cidade.nome)}
            </>
        );
    }


    const header = (
        <div className="flex flex-column md:flex-row md:justify-content-between md:align-items-center">
            <h5 className="m-0">Empresas Cadastradas</h5>
            <span className="block mt-2 md:mt-0 p-input-icon-left">
                <i className="pi pi-search"/>
                <InputText type="search" onInput={(e) => setGlobalFilter(e.target.value)} placeholder="Buscar..."/>
            </span>
        </div>
    );

    const objetoDialogFooter = (
        <>
            <Button label="Cancelar" icon="pi pi-times" className="p-button-text" onClick={hideDialog}/>
            <Button label="Salvar" icon="pi pi-check" className="p-button-text" onClick={saveObjeto}/>
        </>
    );

    const deleteObjetoDialogFooter = (
        <>
            <Button label="Não" icon="pi pi-times" className="p-button-text" onClick={hideDeleteObjetoDialog}/>
            <Button label="Sim" icon="pi pi-check" className="p-button-text" onClick={deleteObjeto}/>
        </>
    );

    return (
        <div className="grid crud-demo">
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
                        <Column field="nome" header="Nome" sortable body={nomeBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                        <Column field="cnpj" header="Cnpj" sortable body={cnpjBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                        <Column field="telefone" header="Telefone" sortable body={telefoneBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                        <Column field="cidade" header="Cidade" body={cidadeBodyTemplate} headerStyle={{width: '14%', minWidth: '10rem'}}></Column>
                        <Column body={rowData => {
                            return <ColunaOpcoes rowData={rowData} editObjeto={editObjeto} confirmDeleteObjeto={confirmDeleteObjeto}/>
                        }}></Column>
                    </DataTable>

                    <Dialog visible={objetoDialog} style={{width: '450px'}} header="Cadastrar/Editar" modal className="p-fluid" footer={objetoDialogFooter} onHide={hideDialog}>

                        <div className="field">
                            <label htmlFor="nome">Nome</label>
                            <InputText id="nome" value={objeto.nome} onChange={(e) => onInputChange(e, 'nome')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.nome})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Nome é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="cnpj">Cnpj</label>
                            <InputMask mask="99.999.999/9999-99" value={objeto.cnpj} onChange={(e) => onInputChange(e, 'cnpj')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.cnpj})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Cnpj é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="telefone">Telefone</label>
                            <InputMask mask="(99) 99999-9999" id="telefone" value={objeto.telefone} onChange={(e) => onInputChange(e, 'telefone')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.telefone})}/>

                            {submitted && !objeto.name && <small className="p-invalid">Telefone é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="endereco">Endereço</label>
                            <InputText id="endereco" value={objeto.endereco} onChange={(e) => onInputChange(e, 'endereco')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.endereco})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Endereço é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="numero">Numero</label>
                            <InputText id="numero" value={objeto.numero} onChange={(e) => onInputChange(e, 'numero')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.numero})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Numero é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="cep">Cep</label>
                            <InputMask mask="99999-999" id="cep" value={objeto.cep} onChange={(e) => onInputChange(e, 'cep')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.cep})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Cep é Obrigatório.</small>}
                        </div>
                        <div className="field">
                            <label htmlFor="nome">Cidade</label>
                            <Dropdown optionLabel="nome" value={objeto.cidade} options={cidades} filter onChange={(e) => onInputChange(e, 'cidade')} placeholder="Selecione uma Cidade"/>
                        </div>
                        <div className="field">
                            <label htmlFor="bairro">Bairro</label>
                            <InputText id="bairro" value={objeto.bairro} onChange={(e) => onInputChange(e, 'bairro')} required autoFocus className={classNames({'p-invalid': submitted && !objeto.bairro})}/>
                            {submitted && !objeto.name && <small className="p-invalid">Bairro é Obrigatório.</small>}
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
        </div>
    );
}

const comparisonFn = function (prevProps, nextProps) {
    return prevProps.location.pathname === nextProps.location.pathname;
};

export default React.memo(Empresa, comparisonFn);
