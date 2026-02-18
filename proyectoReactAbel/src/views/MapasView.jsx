import { useEffect, useState } from 'react';
import { api } from '../services/api';
import { useToast } from '../components/Toast';

export default function MapasView({ onNavigateDetails }) {
  const [mapas, setMapas] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [form, setForm] = useState({ id: '', nombre: '', tipoSala: 'SOTANO' });
  const { showToast } = useToast();

  useEffect(() => { 
    loadMapas(); 
  }, []);

  const loadMapas = async () => {
    setLoading(true);
    setError(null);
    
    console.log('========================================');
    console.log('🗺️ INICIANDO CARGA DE MAPAS');
    console.log('========================================');
    
    try {
      const data = await api.get('/mapas');
      
      let mapasList = [];
      
      if (!data) {
        console.warn(' Respuesta vacía o null');
        mapasList = [];
      } else if (Array.isArray(data)) {
        mapasList = data;
      } else if (data.content && Array.isArray(data.content)) {
        mapasList = data.content;
      } else if (typeof data === 'object') {
        mapasList = [];
      } else {
        mapasList = [];
      }
      
      setMapas(mapasList);
      
      if (mapasList.length === 0) {
        console.log('ℹ️ No hay mapas en la lista (puede ser normal si la BD está vacía)');
      }
      
    } catch (error) {
      setError(error.message);
      showToast('Error al cargar las salas: ' + error.message, 'error');
      setMapas([]);
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    
    if (!form.nombre.trim()) {
      showToast('El nombre no puede estar vacío', 'error');
      return;
    }

    try {
      const payload = { 
        nombre: form.nombre.trim(), 
        tipoSala: form.tipoSala 
      };
      
      console.log('💾 Guardando mapa:', payload, 'ID:', form.id);
      await api.save('/mapas', payload, form.id || null);
      
      showToast(form.id ? 'Sala actualizada correctamente' : 'Sala creada correctamente');
      setForm({ id: '', nombre: '', tipoSala: 'SOTANO' });
      loadMapas();
    } catch (error) {
      console.error('❌ Error al guardar:', error);
      showToast(error.message || 'Error al guardar la sala', 'error');
    }
  };

  const handleEdit = (mapa) => {
    console.log('✏️ Editando mapa:', mapa);
    setForm({ 
      id: mapa.id, 
      nombre: mapa.nombre, 
      tipoSala: mapa.tipoSala 
    });
  };
  
  const handleDelete = async (id) => {
    if (confirm("¿Borrar sala?")) {
      console.log('🗑️ Eliminando mapa ID:', id);
      const success = await api.delete(`/mapas/${id}`);
      
      if (success) {
        showToast('Sala eliminada correctamente');
        loadMapas();
      } else {
        showToast('Error al eliminar la sala', 'error');
      }
    }
  };

  const handleCancel = () => {
    setForm({ id: '', nombre: '', tipoSala: 'SOTANO' });
  };

  console.log('🎨 RENDER - Mapas en estado:', mapas.length, 'Loading:', loading);

  return (
    <div className="animate-fade-in">
      <h2 className="text-isaac-flesh border-b border-border-gray pb-2 mb-4 text-xl font-bold">Gestión de Salas</h2>
      
      {/* FORMULARIO */}
      <form onSubmit={handleSubmit} className="bg-panel-bg p-4 rounded mb-6 border border-dashed border-border-gray grid grid-cols-1 md:grid-cols-3 gap-4 items-end">
        <div className="flex flex-col">
          <label className="text-[10px] text-gray-400 font-bold block mb-1 uppercase">
            Nombre Sala <span className="text-accent-red">*</span>
          </label>
          <input 
            className="w-full bg-black border border-border-gray text-white p-2 rounded text-sm focus:border-accent-red outline-none"
            value={form.nombre} 
            onChange={e => setForm({...form, nombre: e.target.value})} 
            placeholder="Ej: Sala del Tesoro" 
            required 
          />
        </div>

        <div className="flex flex-col">
          <label className="text-[10px] text-gray-400 font-bold block mb-1 uppercase">Tipo de Piso</label>
          <select 
            className="w-full bg-black border border-border-gray text-white p-2 rounded text-sm outline-none focus:border-accent-red"
            value={form.tipoSala} 
            onChange={e => setForm({...form, tipoSala: e.target.value})}
          >
            <option value="SOTANO">Sótano</option>
            <option value="CUEVAS">Cuevas</option>
            <option value="PROFUNDIDADES">Profundidades</option>
            <option value="MATRIZ">Matriz</option>
          </select>
        </div>

        <div className="flex gap-2">
          <button 
            type="submit" 
            className="bg-accent-green hover:brightness-110 text-white font-bold py-2 px-4 rounded text-xs uppercase h-10 transition-all"
          >
            {form.id ? 'ACTUALIZAR' : 'GUARDAR'}
          </button>
          {form.id && (
            <button 
              type="button" 
              onClick={handleCancel}
              className="bg-gray-600 hover:bg-gray-500 text-white font-bold py-2 px-3 rounded text-xs uppercase h-10"
            >
              CANCELAR
            </button>
          )}
        </div>
      </form>

      {/* ESTADO DE CARGA Y ERRORES */}
      {loading && (
        <div className="bg-neutral-900 border border-border-gray rounded p-6 mb-4 text-center">
          <div className="animate-pulse text-gray-400">⏳ Cargando salas...</div>
        </div>
      )}

      {error && (
        <div className="bg-red-900/20 border border-red-500 rounded p-4 mb-4">
          <div className="text-red-400 font-bold mb-2">❌ Error al cargar</div>
          <div className="text-sm text-gray-300">{error}</div>
          <button 
            onClick={loadMapas}
            className="mt-3 bg-accent-blue hover:brightness-110 text-white font-bold py-1 px-3 rounded text-xs uppercase"
          >
            🔄 REINTENTAR
          </button>
        </div>
      )}

      {!loading && !error && (
        <div className="bg-neutral-900 border border-border-gray rounded p-3 mb-4 flex justify-between items-center">
          <span className="text-gray-400 text-sm">
            📊 Total de salas: <span className="text-white font-bold">{mapas.length}</span>
          </span>
          <button 
            onClick={loadMapas}
            className="bg-accent-blue hover:brightness-110 text-white font-bold py-1 px-3 rounded text-xs uppercase"
          >
            🔄 RECARGAR
          </button>
        </div>
      )}

      {/* TABLA */}
      {!loading && !error && (
        <div className="bg-panel-bg rounded border border-border-gray overflow-hidden shadow-lg">
          <table className="w-full text-left">
            <thead className="bg-[#2a2a2a] text-isaac-flesh text-[10px] uppercase">
              <tr>
                <th className="p-3">ID</th>
                <th className="p-3">Nombre</th>
                <th className="p-3">Tipo</th>
                <th className="p-3">Acciones</th>
              </tr>
            </thead>
            <tbody className="divide-y divide-border-gray text-sm">
              {mapas.length > 0 ? (
                mapas.map(m => (
                  <tr key={m.id} className="hover:bg-[#333] transition-colors">
                    <td className="p-3 text-gray-400">{m.id}</td>
                    <td className="p-3">{m.nombre || '—'}</td>
                    <td className="p-3">
                      <span className="bg-neutral-800 px-2 py-1 rounded text-xs border border-gray-700">
                        {m.tipoSala || '—'}
                      </span>
                    </td>
                    <td className="p-3 flex gap-2">
                      <button 
                        onClick={() => onNavigateDetails('mapas', m.id)} 
                        className="bg-accent-purple px-2 py-1 rounded text-[10px] font-bold text-white hover:brightness-110"
                        title="Ver objetos de esta sala"
                      >
                        🗺️ ITEMS
                      </button>
                      <button 
                        onClick={() => handleEdit(m)} 
                        className="bg-accent-blue px-2 py-1 rounded text-[10px] font-bold text-white hover:brightness-110"
                        title="Editar sala"
                      >
                        E
                      </button>
                      <button 
                        onClick={() => handleDelete(m.id)} 
                        className="bg-accent-red px-2 py-1 rounded text-[10px] font-bold text-white hover:brightness-110"
                        title="Eliminar sala"
                      >
                        X
                      </button>
                    </td>
                  </tr>
                ))
              ) : (
                <tr>
                  <td colSpan="4" className="p-8 text-center">
                    <div className="flex flex-col items-center gap-2">
                      <span className="text-4xl">🗺️</span>
                      <span className="text-gray-500">No hay salas registradas</span>
                      <span className="text-gray-600 text-xs">Crea tu primera sala usando el formulario</span>
                    </div>
                  </td>
                </tr>
              )}
            </tbody>
          </table>
        </div>
      )}
    </div>
  );
}