import { createRouter as createRouter, createWebHistory } from 'vue-router'
import { useStore } from 'vuex'

// Import components
import HomeView from '../views/HomeView.vue';
import LoginView from '../views/LoginView.vue';
import LogoutView from '../views/LogoutView.vue';
import RegisterView from '../views/RegisterView.vue';
import StudySessionView from '../views/StudySessionView.vue'
import StudyView from '../views/StudyView.vue';
import CreateDeckView from '../views/CreateDeckView.vue'
import EditDeckView from '../views/EditDeckView.vue'
import EditChooseDeck from '../views/EditChooseView.vue'
import FlashFront from '../components/FlashFront.vue';


/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */
const routes = [
  {
    path: '/',
    name: 'home',
    component: HomeView,
    meta: {
      showHeader: true,
      requiresAuth: true
    }
  },
  

    {
        path: '/session/:deckId',
        name: 'session',
        component: () => import('@/views/StudySessionView.vue'),
        props: true
    },
    {
      path: '/edit-deck/:deckId',
      name: 'editDeck',
      component: () => import('@/views/EditDeckView.vue'),
      props: true,
      meta: {  // Ensure 'meta' is used here
        showHeader: true,
        requiresAuth: true
      }
  },
  {
    path: '/edit',
    name: 'edit',
    component: EditChooseDeck,
    meta: {
      showHeader: true,
      requiresAuth: true
    }
  },

  
  {
    path: '/flashfront',
    name: 'flashfront',
    component: FlashFront,
    meta: {
      showHeader: true,
      requiresAuth: true
    }
  },
  {
    
    path: '/create',
    name: 'create',
    component: CreateDeckView,
    meta: {
      showHeader: true,
      requiresAuth: true
    }
  },
  {
  path: '/study',
  name: 'study',
  component: StudyView,
  meta: {
    showHeader: true,
    requiresAuth: true
  }
},
{
  path: '/study-session/:deckId/:name', 
  name: 'session',                          
  component: StudySessionView,              
  meta: {
    showHeader: true,                       
    requiresAuth: true                      
  }
},
  {
    path: "/login",
    name: "login",
    component: LoginView,
    meta: {
      showHeader: false, 
      requiresAuth: false
    }
  },
  {
    path: "/logout",
    name: "logout",
    component: LogoutView,
    meta: {
      requiresAuth: false
    }
  },
  {
    path: "/register",
    name: "register",
    component: RegisterView,
    meta: {
      requiresAuth: false,
        showHeader: false 
    }
  }
];

// Create the router
const router = createRouter({
  history: createWebHistory(),
  routes: routes
});

router.beforeEach((to) => {

  // Get the Vuex store
  const store = useStore();

  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    return {name: "login"};
  }
  // Otherwise, do nothing and they'll go to their next destination
});

export default router;
